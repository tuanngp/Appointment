package com.appointment.repository;

import com.appointment.entity.SaPipelineStages;
import com.appointment.enums.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaPipelineStagesRepository extends JpaRepository<SaPipelineStages, Long>, JpaSpecificationExecutor<SaPipelineStages> {
    List<SaPipelineStages> findByPipelineId(Long pipelineId);
    List<SaPipelineStages> findByStageId(Long stageId);
    List<SaPipelineStages> findByPipelineIdOrderByPosition(Long pipelineId);
    List<SaPipelineStages> findByStatus(GeneralStatus status);
    List<SaPipelineStages> findByPipelineIdAndStatus(Long pipelineId, GeneralStatus status);
}
