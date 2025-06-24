package com.appointment.dto;

import com.appointment.enums.CustRemindStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CustRemindDto {
    private Long id;

    @NotNull(message = "Due date cannot be null")
    @FutureOrPresent(message = "Due date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private Integer reminderB4Due;
    private Integer reminderInterval;

    private Long custId;
    @Size(max = 25, message = "Customer No must be at most 25 characters")
    private String custNo;
    @Size(max = 250, message = "Customer Full Name must be at most 250 characters")
    private String custFullName;

    private BigDecimal paymentValue;

    private CustRemindStatus status;

    private Long productId;
    private Long calendarId;
}