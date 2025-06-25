package com.appointment.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAppointmentRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 250, message = "Title must be at most 250 characters")
    private String title;

    @Size(max = 1000, message = "Note must be at most 1000 characters")
    private String note;

    private Integer priority;

    @NotNull(message = "Due date time cannot be null")
    @Future(message = "Due date time must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDateTime;

    private Long durationMin;

    private Long custId;

    @Size(max = 25, message = "Customer No must be at most 25 characters")
    private String custNo;

    @Size(max = 250, message = "Customer Full Name must be at most 250 characters")
    private String custFullName;

    @Size(max = 19, message = "Contact Title must be at most 19 characters")
    private String contactTitle;

    @Size(max = 19, message = "Contact Mobile must be at most 19 characters")
    private String contactMobile;

    @Size(max = 250, message = "Contact Full Name must be at most 250 characters")
    private String contactFullName;

    private Integer numOfAttendance;

    @Size(max = 500, message = "Attendance Contact IDs must be at most 500 characters")
    private String attendanceContactIds;

    private Long dealId;
    private String dealName;
    private BigDecimal dealValue;
    private Long calendarId;
    private Long addrBookId;
}
