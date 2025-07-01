package com.appointment.controller;

import com.appointment.dto.request.GetRemindersRequest;
import com.appointment.dto.response.ReminderResponse;
import com.appointment.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final CalendarService calendarService;

    public ReminderController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/appointments")
    public ResponseEntity<List<ReminderResponse>> getUpcomingAppointmentReminders(
            @Valid @RequestBody GetRemindersRequest request) {
        List<ReminderResponse> reminders = calendarService.getUpcomingAppointmentReminders(request);
        return ResponseEntity.ok(reminders);
    }

    @PostMapping("/payments")
    public ResponseEntity<List<ReminderResponse>> getUpcomingPaymentReminders(
            @Valid @RequestBody GetRemindersRequest request) {
        List<ReminderResponse> reminders = calendarService.getUpcomingPaymentReminders(request);
        return ResponseEntity.ok(reminders);
    }

    @PostMapping("/birthdays")
    public ResponseEntity<List<ReminderResponse>> getBirthdayReminders(
            @Valid @RequestBody GetRemindersRequest request) {
        List<ReminderResponse> reminders = calendarService.getUpcomingBirthdays(request);
        return ResponseEntity.ok(reminders);
    }
}
