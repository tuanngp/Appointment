package com.appointment.dto.response;

import lombok.Data;

@Data
public class OpportunityInfo {

    private Long id;

    private String opportunityName;

    private String opportunityType;

    private String customerName;

    private String phoneNumber;

    private String productName;

    private String description;

    private String dealStatus;
}
