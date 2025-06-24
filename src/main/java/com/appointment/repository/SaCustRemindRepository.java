package com.appointment.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appointment.entity.SaCustRemind;
import com.appointment.enums.CustRemindType;

public interface SaCustRemindRepository extends JpaRepository<SaCustRemind, Long> {
    List<SaCustRemind> findByDueDateBetweenAndStatus(LocalDate start, LocalDate end, CustRemindType status);
    List<SaCustRemind> findByDueDateBetweenAndStatusAndCustId(LocalDate start, LocalDate end, CustRemindType status, Long custId);
    List<SaCustRemind> findByDueDateAndStatus(LocalDate date, CustRemindType status);
    List<SaCustRemind> findByDueDateAndStatusAndCustId(LocalDate date, CustRemindType status, Long custId);
    
    @Query("SELECT s FROM SaCustRemind s WHERE s.custId = :custId ORDER BY s.id DESC LIMIT 1")
    Optional<SaCustRemind> findFirstByCustIdOrderByIdDesc(@Param("custId") Long custId);
}
