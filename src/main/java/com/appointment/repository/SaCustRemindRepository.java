package com.appointment.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.appointment.enums.CustRemindStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appointment.entity.SaCustRemind;

public interface SaCustRemindRepository extends JpaRepository<SaCustRemind, Long> {
    List<SaCustRemind> findByDueDateAndStatus(LocalDate date, CustRemindStatus status);
    List<SaCustRemind> findByDueDateAndStatusAndCustomer_CustomerId(LocalDate date, CustRemindStatus status, Long customerId);

    @Query("SELECT s FROM SaCustRemind s WHERE s.customer.customerId = :customerId ORDER BY s.id DESC LIMIT 1")
    Optional<SaCustRemind> findFirstByCustomer_CustomerIdOrderByIdDesc(@Param("customerId") Long customerId);
}
