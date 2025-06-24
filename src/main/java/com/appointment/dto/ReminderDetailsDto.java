package com.appointment.dto;

import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.ReminderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderDetailsDto {
    private ReminderType type;
    private String title;
    private String note;
    private String description;
    private AppointmentStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reminderDateTime;
    private String customerName;
    private String customerMobile;
    private Long referenceId;
}