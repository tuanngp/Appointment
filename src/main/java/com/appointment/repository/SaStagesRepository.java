package com.appointment.repository;

import com.appointment.entity.SaStages;
import com.appointment.enums.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaStagesRepository extends JpaRepository<SaStages, Long>, JpaSpecificationExecutor<SaStages> {
    List<SaStages> findByStatus(GeneralStatus status);
    List<SaStages> findByStatusOrderByPosition(GeneralStatus status);
}
