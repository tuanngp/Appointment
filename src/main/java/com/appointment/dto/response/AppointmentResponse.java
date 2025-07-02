package com.appointment.dto.response;

import com.appointment.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private String title;
    private String note;
    private Integer priority;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDateTime;

    private Long durationMin;
    private Long custId;
    private String custNo;
    private String custFullName;
    private String contactTitle;
    private String contactMobile;
    private String contactFullName;
    private Integer numOfAttendance;
    private String attendanceContactIds;
    private Long dealId;
    private String dealName;
    private BigDecimal dealValue;
    private AppointmentStatus status;
    private Long calendarId;
    private Long addrBookId;

    // Customer address information from SaAddressBook
    private String addressTitle;
    private String addressLine;
    private String fullAddress;
    private Long longitude;
    private Long latitude;
}
