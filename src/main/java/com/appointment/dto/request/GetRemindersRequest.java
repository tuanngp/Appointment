package com.appointment.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetRemindersRequest {
    
    @NotNull(message = "Specific date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate specificDate;
    
    private Long customerId;
}
