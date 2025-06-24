package com.appointment.dto;


import com.appointment.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAppointmentStatusDto {
    @NotBlank(message = "Status cannot be blank")
    private AppointmentStatus status;
}