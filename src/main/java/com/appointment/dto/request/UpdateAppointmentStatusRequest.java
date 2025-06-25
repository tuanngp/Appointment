package com.appointment.dto.request;

import com.appointment.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAppointmentStatusRequest {
    @NotNull(message = "Status cannot be null")
    private AppointmentStatus status;
}
