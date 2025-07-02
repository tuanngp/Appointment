package com.appointment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpportunityListResponse {

    private Long id;

    private String opportunityName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private String contactPhone;

    private String email;

    private String categoryName;

    private String customerName;

    private String dealStatus;
}
