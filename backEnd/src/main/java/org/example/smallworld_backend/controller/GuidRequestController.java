package org.example.smallworld_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.smallworld_backend.EmailSender.EmailSender;
import org.example.smallworld_backend.dto.GuidDTO;
import org.example.smallworld_backend.dto.RequestDTO;
import org.example.smallworld_backend.dto.ResponseDTO;
import org.example.smallworld_backend.entity.Guid;
import org.example.smallworld_backend.service.GuidService;
import org.example.smallworld_backend.service.RequestService;
import org.example.smallworld_backend.service.UserService;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tourRequest")
@CrossOrigin
public class GuidRequestController {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private GuidService guidService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<ResponseDTO> createGuidRequest(@RequestBody RequestDTO requestDTO , HttpServletRequest request) {
        try {
            System.out.println(requestDTO.getGuideId());
            System.out.println(requestDTO.getMessage());
            System.out.println(requestDTO.getCustomerName());
            System.out.println(requestDTO.getTourDuration());
            System.out.println(requestDTO.getCustomerEmail());

            String reqMessage = String.format(
                    "Dear Guide,\n\n" +
                            "I hope this email finds you well. I am reaching out to request a tour with the following details:\n\n" +
                            "- Customer Name: %s\n" +
                            "- Customer Email: %s\n" +
                            "- Customer Phone: %s\n" +
                            "- Group Size: %s\n" +
                            "- Preferred Tour Date: %s\n" +
                            "- Duration of the Tour: %s\n" +
                            "- Additional Message from Customer: %s\n\n" +
                            "Please let us know your availability and any further details regarding the tour. We look forward to your response.\n\n" +
                            "Best regards,\n" +
                            "SmallWorld Team",
                    requestDTO.getCustomerName(),
                    requestDTO.getCustomerEmail(),
                    requestDTO.getCustomerPhone(),
                    requestDTO.getGroupSize(),
                    requestDTO.getTourDate(),
                    requestDTO.getTourDuration(),
                    requestDTO.getMessage()
            );

            String email = guidService.getEmailbyId(requestDTO.getGuideId());

            requestDTO.setUserEmail((String) request.getAttribute("email"));

            int res = requestService.saveRequest(requestDTO);

            switch (res) {
                case VarList.OK -> {
                    emailSender.sendEmail(email, reqMessage);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
                    } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new ResponseDTO(VarList.Bad_Gateway, "Error", e.getMessage()));
        }
    }

    @GetMapping("/getAllById/{id}")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<ResponseDTO> getAllRequests(@PathVariable String id) {
        try {
            System.out.println(id + "id");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", requestService.getAllRequests(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new ResponseDTO(VarList.Bad_Gateway, "Error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}/{status}")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<ResponseDTO> updateRequest(@PathVariable String id, @PathVariable String status) {
        try {
            int res = requestService.updateRequest(id, status);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new ResponseDTO(VarList.Bad_Gateway, "Error", e.getMessage()));
        }
    }
}
