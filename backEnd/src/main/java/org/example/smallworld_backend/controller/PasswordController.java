package org.example.smallworld_backend.controller;

import org.example.smallworld_backend.dto.AuthDTO;
import org.example.smallworld_backend.dto.ResponseDTO;
import org.example.smallworld_backend.dto.UserDTO;
import org.example.smallworld_backend.service.impl.UserServiceImpl;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/v1/password")
@CrossOrigin
public class PasswordController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/sentOTP")
    public String sentOTP(@RequestBody Map<String, String> requestBody) {
        System.out.println("sentOTP");
        try {
            String email = requestBody.get("email");
            System.out.println(email + "  email");
            boolean exists = userService.ifEmailExists(email);
            if (!exists) {
                return "Email does not exist";
            }
            System.out.println("sentOTP");
            int code = (1000 + (int) (Math.random() * 9000));
            sendEmail(email, code);
            return String.valueOf(code);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

        public void sendEmail(String email, int code) {
        new Thread(() -> {
            try {
                String senderEmail = "dilshanbuddhi40@gmail.com";
                String senderPassword = "sdtnymkvqitjcsku"; // Replace with the app-specific password from Gmail

                String subject = "OTP Code from SmallWorld";

                String body = "Dear User,\n\n" +
                        "Your OTP code for accessing SmallWorld services is: " + code + "\n\n" +
                        "Please use this code to verify your identity. The OTP is valid for 10 minutes only.\n" +
                        "If you did not request this OTP, please ignore this email or contact support.\n\n" +
                        "Best regards,\n" +
                        "The SmallWorld Team";

                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                // Create a mail session with authentication
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                try {
                    // Create a MimeMessage object for the email
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderEmail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject(subject);
                    message.setText(body);

                    // Send the email
                    Transport.send(message);

                    System.out.println("OTP sent successfully to " + email);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        }

        @PutMapping("/updatePassword")
        public String updatePassword(@RequestBody UserDTO userDTO){
            try {
                UserDTO exuser = userService.searchUser(userDTO.getEmail());
                exuser.setPassword(userDTO.getPassword());
                System.out.println("updatePassword");
                return "Password updated for "+exuser; // meka gahanna oneee
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @PostMapping("/resetPassword")
        public ResponseEntity<ResponseDTO> resetPassword(@RequestBody UserDTO userDTO){
            try {
                UserDTO exuser = userService.searchUser(userDTO.getEmail());
                exuser.setPassword(userDTO.getPassword());
                System.out.println("updatePassword");
               int res = userService.resetPass(exuser);
                switch (res) {
                    case VarList.Created -> {
                        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ResponseDTO(VarList.Created, "Success", null));
                    }
                    case VarList.Not_Acceptable -> {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                    }
                    default -> {
                        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                    }
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
            }
        }

    }


