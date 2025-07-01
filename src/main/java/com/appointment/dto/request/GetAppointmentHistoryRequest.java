package com.appointment.dto.request;

import lombok.Data;

@Data
public class GetAppointmentHistoryRequest {
    
    private Long customerId;
    
    private String status;
}
