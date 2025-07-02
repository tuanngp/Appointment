package com.appointment.dto.response;

import lombok.Data;

@Data
public class OpportunityDetailResponse {

    private OpportunityInfo opportunityInfo;

    private OpportunityStageStatus stageStatus;

    private AppointmentInfo appointmentInfo;

    private ContractInfo contractInfo;
}
