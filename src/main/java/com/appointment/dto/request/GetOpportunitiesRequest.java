package com.appointment.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetOpportunitiesRequest {

    private String categoryCode;

    private Long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private String customerName;

    private String phoneNumber;

    private String dealStatus;
}
