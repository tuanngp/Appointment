package com.appointment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetOpportunitiesByCategoryRequest {

    @NotBlank(message = "Category code cannot be blank")
    private String categoryCode;

    private Long customerId;

    private String dealStatus;
}
