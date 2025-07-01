package com.appointment.repository;

import com.appointment.entity.SaDeal;
import com.appointment.enums.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaDealRepository extends JpaRepository<SaDeal, Long>, JpaSpecificationExecutor<SaDeal> {
    List<SaDeal> findByCustId(Long custId);
    List<SaDeal> findByDealStatus(DealStatus dealStatus);
    List<SaDeal> findByStaffId(Long staffId);
}
