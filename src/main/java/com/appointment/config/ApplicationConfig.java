package com.appointment.config;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.entity.SaAppointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Set strict matching strategy to avoid ambiguous mappings
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Configure mapping from CreateAppointmentRequest to SaAppointment
        mapper.createTypeMap(CreateAppointmentRequest.class, SaAppointment.class)
                .addMappings(mapping -> {

                    mapping.skip(SaAppointment::setId);

                    mapping.skip(SaAppointment::setCalendar);

                    mapping.map(CreateAppointmentRequest::getTitle, SaAppointment::setTitle);
                    mapping.map(CreateAppointmentRequest::getNote, SaAppointment::setNote);
                    mapping.map(CreateAppointmentRequest::getPriority, SaAppointment::setPriority);
                    mapping.map(CreateAppointmentRequest::getDueDateTime, SaAppointment::setDueDateTime);
                    mapping.map(CreateAppointmentRequest::getDurationMin, SaAppointment::setDurationMin);
                    mapping.map(CreateAppointmentRequest::getCustId, SaAppointment::setCustId);
                    mapping.map(CreateAppointmentRequest::getCustNo, SaAppointment::setCustNo);
                    mapping.map(CreateAppointmentRequest::getCustFullName, SaAppointment::setCustFullName);
                    mapping.map(CreateAppointmentRequest::getContactTitle, SaAppointment::setContactTitle);
                    mapping.map(CreateAppointmentRequest::getContactMobile, SaAppointment::setContactMobile);
                    mapping.map(CreateAppointmentRequest::getContactFullName, SaAppointment::setContactFullName);
                    mapping.map(CreateAppointmentRequest::getNumOfAttendance, SaAppointment::setNumOfAttendance);
                    mapping.map(CreateAppointmentRequest::getAttendanceContactIds, SaAppointment::setAttendanceContactIds);
                    mapping.map(CreateAppointmentRequest::getDealId, SaAppointment::setDealId);
                    mapping.map(CreateAppointmentRequest::getDealName, SaAppointment::setDealName);
                    mapping.map(CreateAppointmentRequest::getDealValue, SaAppointment::setDealValue);
                    mapping.map(CreateAppointmentRequest::getAddrBookId, SaAppointment::setAddrBookId);
                });

        return mapper;
    }
}