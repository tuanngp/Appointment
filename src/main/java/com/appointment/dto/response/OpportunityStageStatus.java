package com.appointment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OpportunityStageStatus {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approachDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime profileCreationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approvalWaitingDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resultDate;

    // New fields for pipeline-based stage tracking
    private String currentStageName;
    private Long currentStageId;
    private String pipelineName;
    private Long pipelineId;
    private List<StageHistoryItem> stageHistory;

    @Data
    public static class StageHistoryItem {
        private Long stageId;
        private String stageName;
        private String description;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime beginDate;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;

        private Long durationMinutes;
        private boolean isActive;
    }
}
