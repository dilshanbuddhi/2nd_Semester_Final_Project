package org.example.smallworld_backend.dto;

import jdk.dynalink.NamedOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDTO {

    private String userEmail;

    private String customerEmail;
    private String customerName;
    private String customerPhone;
    private String groupSize;
    private String guideId;
    private String message;
    private String tourDate;
    private String tourDuration;
    private String language;
    private String status;
    private String title;

    public RequestDTO(String customerEmail, String customerName, String customerPhone, String groupSize, String guideId, String message, String tourDate, String tourDuration, String language, String status , String title) {
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.groupSize = groupSize;
        this.guideId = guideId;
        this.message = message;
        this.tourDate = tourDate;
        this.tourDuration = tourDuration;
        this.language = language;
        this.status = status;
        this.title = title;
    }
}
