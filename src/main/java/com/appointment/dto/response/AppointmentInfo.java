package com.appointment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentInfo {

    private Long appointmentId;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentDateTime;

    private String note;

    private String status;

    private Long durationMin;

    private String contactMobile;
}
