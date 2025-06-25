package com.appointment.dto;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.dto.request.UpdateAppointmentStatusRequest;
import com.appointment.dto.response.AppointmentResponse;
import com.appointment.dto.response.ReminderResponse;
import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.ReminderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DtoRefactoringTest {

    @Test
    void testCreateAppointmentRequest() {
        CreateAppointmentRequest request = new CreateAppointmentRequest();
        request.setTitle("Test Appointment");
        request.setNote("Test Note");
        request.setPriority(1);
        request.setDueDateTime(LocalDateTime.now().plusDays(1));
        request.setDurationMin(60L);
        request.setCustId(1L);
        request.setCustNo("CUST001");
        request.setCustFullName("John Doe");
        request.setContactTitle("Mr.");
        request.setContactMobile("1234567890");
        request.setContactFullName("John Doe");
        request.setNumOfAttendance(2);
        request.setAttendanceContactIds("1,2");
        request.setDealId(1L);
        request.setDealName("Test Deal");
        request.setDealValue(new BigDecimal("1000.00"));
        request.setCalendarId(1L);
        request.setAddrBookId(1L);

        assertNotNull(request);
        assertEquals("Test Appointment", request.getTitle());
        assertEquals("Test Note", request.getNote());
        assertEquals(1, request.getPriority());
        assertEquals(60L, request.getDurationMin());
        assertEquals(1L, request.getCustId());
        assertEquals("CUST001", request.getCustNo());
        assertEquals("John Doe", request.getCustFullName());
    }

    @Test
    void testUpdateAppointmentStatusRequest() {
        UpdateAppointmentStatusRequest request = new UpdateAppointmentStatusRequest();
        request.setStatus(AppointmentStatus.Active);

        assertNotNull(request);
        assertEquals(AppointmentStatus.Active, request.getStatus());
    }

    @Test
    void testAppointmentResponse() {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(1L);
        response.setTitle("Test Appointment");
        response.setNote("Test Note");
        response.setPriority(1);
        response.setDueDateTime(LocalDateTime.now().plusDays(1));
        response.setDurationMin(60L);
        response.setCustId(1L);
        response.setCustNo("CUST001");
        response.setCustFullName("John Doe");
        response.setContactTitle("Mr.");
        response.setContactMobile("1234567890");
        response.setContactFullName("John Doe");
        response.setNumOfAttendance(2);
        response.setAttendanceContactIds("1,2");
        response.setDealId(1L);
        response.setDealName("Test Deal");
        response.setDealValue(new BigDecimal("1000.00"));
        response.setStatus(AppointmentStatus.Active);
        response.setCalendarId(1L);
        response.setAddrBookId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Appointment", response.getTitle());
        assertEquals("Test Note", response.getNote());
        assertEquals(AppointmentStatus.Active, response.getStatus());
    }

    @Test
    void testReminderResponse() {
        ReminderResponse response = new ReminderResponse();
        response.setType(ReminderType.Appointment);
        response.setTitle("Test Reminder");
        response.setNote("Test Note");
        response.setDescription("Test Description");
        response.setStatus(AppointmentStatus.Active);
        response.setReminderDateTime(LocalDateTime.now().plusDays(1));
        response.setCustomerName("John Doe");
        response.setCustomerMobile("1234567890");
        response.setReferenceId(1L);

        assertNotNull(response);
        assertEquals(ReminderType.Appointment, response.getType());
        assertEquals("Test Reminder", response.getTitle());
        assertEquals("Test Note", response.getNote());
        assertEquals("Test Description", response.getDescription());
        assertEquals(AppointmentStatus.Active, response.getStatus());
        assertEquals("John Doe", response.getCustomerName());
        assertEquals("1234567890", response.getCustomerMobile());
        assertEquals(1L, response.getReferenceId());
    }
}
