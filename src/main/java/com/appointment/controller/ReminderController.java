package com.appointment.controller;

import com.appointment.dto.ReminderDetailsDto;
import com.appointment.service.CalendarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final CalendarService calendarService;

    public ReminderController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<ReminderDetailsDto>> getUpcomingAppointmentReminders(
            @RequestParam(name = "specificDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate specificDate,
            @RequestParam(name = "customerId", required = false) Long customerId) {
        List<ReminderDetailsDto> reminders = calendarService.getUpcomingAppointmentReminders(specificDate, customerId);
        return ResponseEntity.ok(reminders);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<ReminderDetailsDto>> getUpcomingPaymentReminders(
            @RequestParam(name = "specificDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate specificDate,
            @RequestParam(name = "customerId", required = false) Long customerId) {
        List<ReminderDetailsDto> reminders = calendarService.getUpcomingPaymentReminders(specificDate, customerId);
        return ResponseEntity.ok(reminders);
    }

    @GetMapping("/birthdays")
    public ResponseEntity<List<ReminderDetailsDto>> getBirthdayReminders(
            @RequestParam(name = "specificDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate specificDate,
            @RequestParam(name = "customerId", required = false) Long customerId) {
        List<ReminderDetailsDto> reminders = calendarService.getUpcomingBirthdays(specificDate, customerId);
        return ResponseEntity.ok(reminders);
    }
}
