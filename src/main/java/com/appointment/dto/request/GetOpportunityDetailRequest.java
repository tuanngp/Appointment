package com.appointment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetOpportunityDetailRequest {

    @NotNull(message = "Opportunity ID cannot be null")
    private Long opportunityId;
}
