package com.appointment.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PipelineStageResponse {
    private Long id;
    private Long pipelineId;
    private Long stageId;
    private String stageName;
    private Long position;
    private BigDecimal probability;
    private String stageDescription;
    private String status;
}
