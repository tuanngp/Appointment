package com.appointment.repository;

import com.appointment.entity.SaPipelines;
import com.appointment.enums.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaPipelinesRepository extends JpaRepository<SaPipelines, Long>, JpaSpecificationExecutor<SaPipelines> {
    List<SaPipelines> findByStatus(GeneralStatus status);
    List<SaPipelines> findByProductId(Long productId);
    List<SaPipelines> findByStatusOrderByPosition(GeneralStatus status);
}
