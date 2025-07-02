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

    List<SaAppointment> findByDueDateTimeBetweenAndStatusAndCustomer_CustomerId(LocalDateTime start, LocalDateTime end, AppointmentStatus status, Long customerId);

    @Query("SELECT s FROM SaAppointment s WHERE s.customer.customerId = :customerId ORDER BY s.id DESC LIMIT 1")
    Optional<SaAppointment> findFirstByCustomer_CustomerIdOrderByIdDesc(@Param("customerId") Long customerId);

    List<SaAppointment> findByDealId(Long dealId);
}
