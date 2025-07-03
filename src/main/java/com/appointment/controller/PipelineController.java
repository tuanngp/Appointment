package com.appointment.controller;

import com.appointment.dto.request.AdvanceOpportunityStageRequest;
import com.appointment.dto.response.PipelineResponse;
import com.appointment.dto.response.PipelineStageResponse;
import com.appointment.entity.SaPipelines;
import com.appointment.entity.SaPipelineStages;
import com.appointment.entity.SaStages;
import com.appointment.service.OpportunityService;
import com.appointment.service.PipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/pipelines")
public class PipelineController {

    private final PipelineService pipelineService;
    private final OpportunityService opportunityService;

    public PipelineController(PipelineService pipelineService, OpportunityService opportunityService) {
        this.pipelineService = pipelineService;
        this.opportunityService = opportunityService;
    }

    @PostMapping("/get-all")
    public ResponseEntity<List<PipelineResponse>> getAllPipelines() {
        List<SaPipelines> pipelines = pipelineService.getAllActivePipelines();
        
        List<PipelineResponse> responses = pipelines.stream()
                .map(this::convertToPipelineResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/get-by-product")
    public ResponseEntity<List<PipelineResponse>> getPipelinesByProduct(@RequestParam Long productId) {
        List<SaPipelines> pipelines = pipelineService.getPipelinesByProductId(productId);
        
        List<PipelineResponse> responses = pipelines.stream()
                .map(this::convertToPipelineResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/get-stages")
    public ResponseEntity<List<PipelineStageResponse>> getPipelineStages(@RequestParam Long pipelineId) {
        List<SaPipelineStages> pipelineStages = pipelineService.getPipelineStages(pipelineId);
        
        List<PipelineStageResponse> responses = pipelineStages.stream()
                .map(this::convertToPipelineStageResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/advance-opportunity-stage")
    public ResponseEntity<String> advanceOpportunityStage(@RequestBody AdvanceOpportunityStageRequest request) {
        try {
            opportunityService.advanceOpportunityStage(request.getOpportunityId(), request.getDescription());
            return ResponseEntity.ok("Opportunity stage advanced successfully");
        } catch (Exception e) {
            log.error("Error advancing opportunity stage", e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    private PipelineResponse convertToPipelineResponse(SaPipelines pipeline) {
        PipelineResponse response = new PipelineResponse();
        response.setId(pipeline.getId());
        response.setName(pipeline.getName());
        response.setPosition(pipeline.getPosition());
        response.setDescription(pipeline.getDescription());
        response.setProductId(pipeline.getProductId());
        response.setStatus(pipeline.getStatus().name());
        
        if (pipeline.getProduct() != null) {
            response.setProductName(pipeline.getProduct().getProductName());
        }
        
        // Get stages for this pipeline
        List<SaPipelineStages> pipelineStages = pipelineService.getPipelineStages(pipeline.getId());
        List<PipelineStageResponse> stageResponses = pipelineStages.stream()
                .map(this::convertToPipelineStageResponse)
                .collect(Collectors.toList());
        response.setStages(stageResponses);
        
        return response;
    }

    private PipelineStageResponse convertToPipelineStageResponse(SaPipelineStages pipelineStage) {
        PipelineStageResponse response = new PipelineStageResponse();
        response.setId(pipelineStage.getId());
        response.setPipelineId(pipelineStage.getPipelineId());
        response.setStageId(pipelineStage.getStageId());
        response.setPosition(pipelineStage.getPosition());
        response.setStatus(pipelineStage.getStatus().name());
        
        // Get stage details
        if (pipelineStage.getStage() != null) {
            SaStages stage = pipelineStage.getStage();
            response.setStageName(stage.getName());
            response.setProbability(stage.getProbability());
            response.setStageDescription(stage.getDescription());
        }
        
        return response;
    }
}
