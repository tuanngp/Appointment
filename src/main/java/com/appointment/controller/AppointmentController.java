package com.appointment.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.dto.AppointmentDto;
import com.appointment.dto.ReminderDetailsDto;
import com.appointment.dto.UpdateAppointmentStatusDto;
import com.appointment.enums.AppointmentStatus;
import com.appointment.service.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentDto>> getAppointments(
            @RequestParam(name = "status", required = false) AppointmentStatus status,
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "startDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @PageableDefault(size = 10, sort = "dueDateTime") Pageable pageable) {
        Page<AppointmentDto> appointments = appointmentService.getAppointments(status, customerId, startDate, endDate, pageable);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDto> updateAppointmentStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentStatusDto statusDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointmentStatus(id, statusDto.getStatus());
        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/history/last30days")
    public ResponseEntity<List<AppointmentDto>> getAppointmentHistoryLast30Days(
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "status", required = false) String status) {
        List<AppointmentDto> history = appointmentService.getAppointmentHistoryLast30Days(customerId, status);
        return ResponseEntity.ok(history);
    }
}