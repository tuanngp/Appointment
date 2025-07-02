package com.appointment.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateOpportunityRequest {

    @NotBlank(message = "Opportunity name cannot be blank")
    @Size(max = 250, message = "Opportunity name must be at most 250 characters")
    private String opportunityName;

    @NotNull(message = "Interested product ID cannot be null")
    private Long interestedProductId;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(max = 19, message = "Phone number must be at most 19 characters")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentDateTime;

    @Size(max = 1000, message = "Note must be at most 1000 characters")
    private String note;

    private Integer priority;

    private Long durationMin;
}
