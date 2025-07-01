package com.appointment.mapper;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.dto.response.AppointmentResponse;
import com.appointment.entity.SaAppointment;
import com.appointment.entity.SaCustomer;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    /**
     * Chuyển đổi CreateAppointmentRequest thành SaAppointment entity
     */
    public SaAppointment toEntity(CreateAppointmentRequest request) {
        if (request == null) {
            return null;
        }

        SaAppointment appointment = new SaAppointment();
        
        // Map các fields trực tiếp
        appointment.setTitle(request.getTitle());
        appointment.setNote(request.getNote());
        appointment.setPriority(request.getPriority());
        appointment.setDueDateTime(request.getDueDateTime());
        appointment.setDurationMin(request.getDurationMin());
        appointment.setCustNo(request.getCustNo());
        appointment.setCustFullName(request.getCustFullName());
        appointment.setContactTitle(request.getContactTitle());
        appointment.setContactMobile(request.getContactMobile());
        appointment.setContactFullName(request.getContactFullName());
        appointment.setNumOfAttendance(request.getNumOfAttendance());
        appointment.setAttendanceContactIds(request.getAttendanceContactIds());
        appointment.setDealId(request.getDealId());
        appointment.setDealName(request.getDealName());
        appointment.setDealValue(request.getDealValue());
        appointment.setAddrBookId(request.getAddrBookId());

        // Custom mapping cho customer - tạo SaCustomer object từ custId
        if (request.getCustId() != null) {
            SaCustomer customer = new SaCustomer();
            customer.setCustomerId(request.getCustId());
            appointment.setCustomer(customer);
        }

        // Note: id, calendar, status sẽ được set trong service layer
        
        return appointment;
    }

    /**
     * Chuyển đổi SaAppointment entity thành AppointmentResponse
     */
    public AppointmentResponse toResponse(SaAppointment appointment) {
        if (appointment == null) {
            return null;
        }

        AppointmentResponse response = new AppointmentResponse();
        
        // Map các fields trực tiếp
        response.setId(appointment.getId());
        response.setTitle(appointment.getTitle());
        response.setNote(appointment.getNote());
        response.setPriority(appointment.getPriority());
        response.setDueDateTime(appointment.getDueDateTime());
        response.setDurationMin(appointment.getDurationMin());
        response.setCustNo(appointment.getCustNo());
        response.setCustFullName(appointment.getCustFullName());
        response.setContactTitle(appointment.getContactTitle());
        response.setContactMobile(appointment.getContactMobile());
        response.setContactFullName(appointment.getContactFullName());
        response.setNumOfAttendance(appointment.getNumOfAttendance());
        response.setAttendanceContactIds(appointment.getAttendanceContactIds());
        response.setDealId(appointment.getDealId());
        response.setDealName(appointment.getDealName());
        response.setDealValue(appointment.getDealValue());
        response.setStatus(appointment.getStatus());
        response.setAddrBookId(appointment.getAddrBookId());

        // Extract custId từ customer relationship (tránh proxy issues)
        if (appointment.getCustomer() != null) {
            response.setCustId(appointment.getCustomer().getCustomerId());
        }

        // Extract calendarId từ calendar relationship (tránh proxy issues)
        if (appointment.getCalendar() != null) {
            response.setCalendarId(appointment.getCalendar().getId());
        }

        return response;
    }
}
