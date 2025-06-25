package com.appointment.service;

import com.appointment.dto.response.ReminderResponse;
import com.appointment.entity.SaAppointment;
import com.appointment.entity.SaCalendar;
import com.appointment.entity.SaCustRemind;
import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.CalendarType;
import com.appointment.enums.CustRemindType;
import com.appointment.enums.ReminderType;
import com.appointment.repository.SaAppointmentRepository;
import com.appointment.repository.SaCalendarRepository;
import com.appointment.repository.SaCustRemindRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ReminderResponse> getUpcomingAppointmentReminders(LocalDate specificDate, Long customerId) {
        LocalDateTime startOfDay = specificDate.atStartOfDay();
        LocalDateTime endOfDay = specificDate.atTime(23, 59, 59);

        List<SaAppointment> appointments;
        if (customerId != null) {
            appointments = appointmentRepository.findByDueDateTimeBetweenAndStatusAndCustId(startOfDay, endOfDay, AppointmentStatus.Active, customerId);
        } else {
            appointments = appointmentRepository.findByDueDateTimeBetweenAndStatus(startOfDay, endOfDay, AppointmentStatus.Active);
        }

        return appointments.stream()
                .map(this::mapToReminderResponse)
                .collect(Collectors.toList());
    }

    public List<ReminderResponse> getUpcomingBirthdays(LocalDate specificDate, Long customerId) {
        List<ReminderResponse> birthdayReminders = new ArrayList<>();
        List<SaCalendar> birthdaysOnDate;

        if (customerId != null) {
            birthdaysOnDate = calendarRepository.findByTypeAndEventDateMonthAndEventDateDayAndCustId(
                    CalendarType.BIRTHDAY, specificDate.getMonthValue(), specificDate.getDayOfMonth(), customerId);
        } else {
            birthdaysOnDate = calendarRepository.findByTypeAndEventDateMonthAndEventDateDay(
                    CalendarType.BIRTHDAY, specificDate.getMonthValue(), specificDate.getDayOfMonth());
        }

        for (SaCalendar birthdayEntry : birthdaysOnDate) {
            ReminderResponse dto = new ReminderResponse();
            dto.setType(ReminderType.Birthday);
            dto.setTitle("Birthday of " + (birthdayEntry.getTitle() != null ? birthdayEntry.getTitle() : "Customer"));
            dto.setDescription("Wish them a happy birthday!");
            dto.setReminderDateTime(specificDate.atStartOfDay());
            dto.setReferenceId(birthdayEntry.getId());

            if (birthdayEntry.getCustId() != null) {
                appointmentRepository.findFirstByCustIdOrderByIdDesc(birthdayEntry.getCustId())
                        .ifPresentOrElse(appointment -> {
                            dto.setCustomerName(appointment.getCustFullName());
                            dto.setCustomerMobile(appointment.getContactMobile());
                        }, () -> custRemindRepository.findFirstByCustIdOrderByIdDesc(birthdayEntry.getCustId())
                                .ifPresent(custRemind -> dto.setCustomerName(custRemind.getCustFullName())));
            }

            birthdayReminders.add(dto);
        }
        return birthdayReminders;
    }

    public List<ReminderResponse> getUpcomingPaymentReminders(LocalDate specificDate, Long customerId) {
        List<SaCustRemind> reminders;
        if (customerId != null) {
            reminders = custRemindRepository.findByDueDateAndStatusAndCustId(specificDate, CustRemindType.Active, customerId);
        } else {
            reminders = custRemindRepository.findByDueDateAndStatus(specificDate, CustRemindType.Active);
        }

        return reminders.stream()
                .map(this::mapToReminderResponse)
                .collect(Collectors.toList());
    }

    private ReminderResponse mapToReminderResponse(SaCustRemind custRemind) {
        ReminderResponse dto = new ReminderResponse();
        dto.setType(ReminderType.Payment);
        dto.setTitle("Payment Due: " + custRemind.getPaymentValue());
        dto.setDescription("Payment for product ID: " + custRemind.getProductId() + " is due.");
        dto.setReminderDateTime(custRemind.getDueDate().atStartOfDay()); // Convert LocalDate to LocalDateTime
        dto.setCustomerName(custRemind.getCustFullName());
        dto.setReferenceId(custRemind.getId());
        return dto;
    }

    private ReminderResponse mapToReminderResponse(SaAppointment appointment) {
        ReminderResponse dto = new ReminderResponse();
        dto.setType(ReminderType.Appointment);
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