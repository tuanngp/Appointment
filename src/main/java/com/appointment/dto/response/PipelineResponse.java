package com.appointment.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PipelineResponse {
    private Long id;
    private String name;
    private Integer position;
    private String description;
    private Long productId;
    private String productName;
    private String status;
    private List<PipelineStageResponse> stages;
}
