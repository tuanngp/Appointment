package com.appointment.service;

import com.appointment.dto.request.GetRemindersRequest;
import com.appointment.dto.response.ReminderResponse;
import com.appointment.entity.SaAppointment;
import com.appointment.entity.SaCalendar;
import com.appointment.entity.SaCustRemind;
import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.CalendarType;
import com.appointment.enums.CustRemindStatus;
import com.appointment.enums.ReminderType;
import com.appointment.repository.SaAppointmentRepository;
import com.appointment.repository.SaCalendarRepository;
import com.appointment.repository.SaCustRemindRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final SaCalendarRepository calendarRepository;
    private final SaCustRemindRepository custRemindRepository;
    private final SaAppointmentRepository appointmentRepository;

    public CalendarService(SaCalendarRepository calendarRepository,
                           SaCustRemindRepository custRemindRepository,
                           SaAppointmentRepository appointmentRepository) {
        this.calendarRepository = calendarRepository;
        this.custRemindRepository = custRemindRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<ReminderResponse> getUpcomingAppointmentReminders(GetRemindersRequest request) {
        LocalDateTime startOfDay = request.getSpecificDate().atStartOfDay();
        LocalDateTime endOfDay = request.getSpecificDate().atTime(23, 59, 59);

        List<SaAppointment> appointments;
        if (request.getCustomerId() != null) {
            appointments = appointmentRepository.findByDueDateTimeBetweenAndStatusAndCustomer_CustomerId(startOfDay, endOfDay, AppointmentStatus.A, request.getCustomerId());
        } else {
            appointments = appointmentRepository.findByDueDateTimeBetweenAndStatus(startOfDay, endOfDay, AppointmentStatus.A);
        }

        return appointments.stream()
                .map(this::mapToReminderResponse)
                .collect(Collectors.toList());
    }

    public List<ReminderResponse> getUpcomingBirthdays(GetRemindersRequest request) {
        List<ReminderResponse> birthdayReminders = new ArrayList<>();
        List<SaCalendar> birthdaysOnDate;

        if (request.getCustomerId() != null) {
            birthdaysOnDate = calendarRepository.findByTypeAndEventDateMonthAndEventDateDayAndCustomer_CustomerId(
                    CalendarType.B, request.getSpecificDate().getMonthValue(), request.getSpecificDate().getDayOfMonth(), request.getCustomerId());
        } else {
            birthdaysOnDate = calendarRepository.findByTypeAndEventDateMonthAndEventDateDay(
                    CalendarType.B, request.getSpecificDate().getMonthValue(), request.getSpecificDate().getDayOfMonth());
        }

        for (SaCalendar birthdayEntry : birthdaysOnDate) {
            ReminderResponse dto = new ReminderResponse();
            dto.setType(ReminderType.B);
            dto.setTitle("Birthday of " + (birthdayEntry.getTitle() != null ? birthdayEntry.getTitle() : "Customer"));
            dto.setDescription("Wish them a happy birthday!");
            dto.setReminderDateTime(request.getSpecificDate().atStartOfDay());
            dto.setReferenceId(birthdayEntry.getId());

            if (birthdayEntry.getCustId() != null) {
                appointmentRepository.findFirstByCustomer_CustomerIdOrderByIdDesc(birthdayEntry.getCustId())
                        .ifPresentOrElse(appointment -> {
                            dto.setCustomerName(appointment.getCustFullName());
                            dto.setCustomerMobile(appointment.getContactMobile());
                        }, () -> custRemindRepository.findFirstByCustomer_CustomerIdOrderByIdDesc(birthdayEntry.getCustId())
                                .ifPresent(custRemind -> dto.setCustomerName(custRemind.getCustFullName())));
            }

            birthdayReminders.add(dto);
        }
        return birthdayReminders;
    }

    public List<ReminderResponse> getUpcomingPaymentReminders(GetRemindersRequest request) {
        List<SaCustRemind> reminders;
        if (request.getCustomerId() != null) {
            reminders = custRemindRepository.findByDueDateAndStatusAndCustomer_CustomerId(request.getSpecificDate(), CustRemindStatus.A, request.getCustomerId());
        } else {
            reminders = custRemindRepository.findByDueDateAndStatus(request.getSpecificDate(), CustRemindStatus.A);
        }

        return reminders.stream()
                .map(this::mapToReminderResponse)
                .collect(Collectors.toList());
    }

    private ReminderResponse mapToReminderResponse(SaCustRemind custRemind) {
        ReminderResponse dto = new ReminderResponse();
        dto.setType(ReminderType.P);
        dto.setTitle("Payment Due: " + custRemind.getPaymentValue());
        dto.setDescription("Payment for product ID: " + custRemind.getProductId() + " is due.");
        dto.setReminderDateTime(custRemind.getDueDate().atStartOfDay()); // Convert LocalDate to LocalDateTime
        dto.setCustomerName(custRemind.getCustFullName());
        dto.setReferenceId(custRemind.getId());
        return dto;
    }

    private ReminderResponse mapToReminderResponse(SaAppointment appointment) {
        ReminderResponse dto = new ReminderResponse();
        dto.setType(ReminderType.A);
        dto.setTitle(appointment.getTitle());
        dto.setDescription(appointment.getNote());
        dto.setReminderDateTime(appointment.getDueDateTime());
        dto.setCustomerName(appointment.getCustFullName());
        dto.setCustomerMobile(appointment.getContactMobile());
        dto.setReferenceId(appointment.getId());
        dto.setNote(appointment.getNote());
        dto.setStatus(appointment.getStatus());
        return dto;
    }
}