package com.appointment.controller;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.dto.request.UpdateAppointmentStatusRequest;
import com.appointment.dto.response.AppointmentResponse;
import com.appointment.enums.AppointmentStatus;
import com.appointment.service.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> getAppointments(
            @RequestParam(name = "status", required = false) AppointmentStatus status,
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "startDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @PageableDefault(size = 10, sort = "dueDateTime") Pageable pageable) {
        Page<AppointmentResponse> appointments = appointmentService.getAppointments(status, customerId, startDate, endDate, pageable);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
        AppointmentResponse createdAppointment = appointmentService.createAppointment(request);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponse> updateAppointmentStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentStatusRequest statusRequest) {
        AppointmentResponse updatedAppointment = appointmentService.updateAppointmentStatus(id, statusRequest.getStatus());
        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/history/last30days")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentHistoryLast30Days(
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "status", required = false) String status) {
        List<AppointmentResponse> history = appointmentService.getAppointmentHistoryLast30Days(customerId, status);
        return ResponseEntity.ok(history);
    }
}