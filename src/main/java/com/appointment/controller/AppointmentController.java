package com.appointment.controller;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.dto.request.GetAppointmentsRequest;
import com.appointment.dto.request.GetAppointmentHistoryRequest;
import com.appointment.dto.request.UpdateAppointmentStatusRequest;
import com.appointment.dto.response.AppointmentResponse;
import com.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/search")
    public ResponseEntity<Page<AppointmentResponse>> getAppointments(
            @Valid @RequestBody GetAppointmentsRequest request,
            @PageableDefault(size = 10, sort = "dueDateTime") Pageable pageable) {
        Page<AppointmentResponse> appointments = appointmentService.getAppointments(request, pageable);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
        AppointmentResponse createdAppointment = appointmentService.createAppointment(request);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<AppointmentResponse> updateAppointmentStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentStatusRequest statusRequest) {
        AppointmentResponse updatedAppointment = appointmentService.updateAppointmentStatus(id, statusRequest.getStatus());
        return ResponseEntity.ok(updatedAppointment);
    }

    @PostMapping("/history/last30days")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentHistoryLast30Days(
            @Valid @RequestBody GetAppointmentHistoryRequest request) {
        List<AppointmentResponse> history = appointmentService.getAppointmentHistoryLast30Days(request);
        return ResponseEntity.ok(history);
    }
}