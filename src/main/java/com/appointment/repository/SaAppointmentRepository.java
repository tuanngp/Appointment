package com.appointment.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appointment.entity.SaAppointment;
import com.appointment.enums.AppointmentStatus;

public interface SaAppointmentRepository extends JpaRepository<SaAppointment, Long>, JpaSpecificationExecutor<SaAppointment> {
    List<SaAppointment> findByDueDateTimeBetweenAndStatus(LocalDateTime start, LocalDateTime end, AppointmentStatus status);

    List<SaAppointment> findByDueDateTimeBetweenAndStatusAndCustId(LocalDateTime start, LocalDateTime end, AppointmentStatus status, Long custId);
    
    @Query("SELECT s FROM SaAppointment s WHERE s.custId = :custId ORDER BY s.id DESC LIMIT 1")
    Optional<SaAppointment> findFirstByCustIdOrderByIdDesc(@Param("custId") Long custId);
}
