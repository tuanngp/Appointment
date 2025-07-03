package com.appointment.service;

import com.appointment.entity.SaPipelines;
import com.appointment.entity.SaPipelineStages;
import com.appointment.entity.SaStages;
import com.appointment.enums.GeneralStatus;
import com.appointment.exception.ResourceNotFoundException;
import com.appointment.repository.SaPipelinesRepository;
import com.appointment.repository.SaPipelineStagesRepository;
import com.appointment.repository.SaStagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PipelineService {

    private final SaPipelinesRepository pipelinesRepository;
    private final SaPipelineStagesRepository pipelineStagesRepository;
    private final SaStagesRepository stagesRepository;

    public PipelineService(SaPipelinesRepository pipelinesRepository,
                          SaPipelineStagesRepository pipelineStagesRepository,
                          SaStagesRepository stagesRepository) {
        this.pipelinesRepository = pipelinesRepository;
        this.pipelineStagesRepository = pipelineStagesRepository;
        this.stagesRepository = stagesRepository;
    }

    /**
     * Get all active pipelines
     */
    public List<SaPipelines> getAllActivePipelines() {
        return pipelinesRepository.findByStatusOrderByPosition(GeneralStatus.A);
    }

    /**
     * Get pipeline by ID
     */
    public SaPipelines getPipelineById(Long pipelineId) {
        return pipelinesRepository.findById(pipelineId)
                .orElseThrow(() -> new ResourceNotFoundException("Pipeline not found with ID: " + pipelineId));
    }

    /**
     * Get pipelines by product ID
     */
    public List<SaPipelines> getPipelinesByProductId(Long productId) {
        return pipelinesRepository.findByProductId(productId);
    }

    /**
     * Get all stages for a specific pipeline
     */
    public List<SaPipelineStages> getPipelineStages(Long pipelineId) {
        return pipelineStagesRepository.findByPipelineIdOrderByPosition(pipelineId);
    }

    /**
     * Get all active stages
     */
    public List<SaStages> getAllActiveStages() {
        return stagesRepository.findByStatusOrderByPosition(GeneralStatus.A);
    }

    /**
     * Get stage by ID
     */
    public SaStages getStageById(Long stageId) {
        return stagesRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found with ID: " + stageId));
    }

    /**
     * Get the first stage of a pipeline (for new deals)
     */
    public SaPipelineStages getFirstStageOfPipeline(Long pipelineId) {
        List<SaPipelineStages> stages = pipelineStagesRepository.findByPipelineIdOrderByPosition(pipelineId);
        if (stages.isEmpty()) {
            throw new ResourceNotFoundException("No stages found for pipeline ID: " + pipelineId);
        }
        return stages.get(0);
    }

    /**
     * Get the next stage in a pipeline
     */
    public SaPipelineStages getNextStageInPipeline(Long pipelineId, Long currentPosition) {
        List<SaPipelineStages> stages = pipelineStagesRepository.findByPipelineIdOrderByPosition(pipelineId);
        
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getPosition().equals(currentPosition) && i + 1 < stages.size()) {
                return stages.get(i + 1);
            }
        }
        
        return null; // No next stage (already at the end)
    }

    /**
     * Check if a stage is the last stage in a pipeline
     */
    public boolean isLastStageInPipeline(Long pipelineId, Long position) {
        List<SaPipelineStages> stages = pipelineStagesRepository.findByPipelineIdOrderByPosition(pipelineId);
        if (stages.isEmpty()) {
            return true;
        }
        
        SaPipelineStages lastStage = stages.get(stages.size() - 1);
        return lastStage.getPosition().equals(position);
    }
}
