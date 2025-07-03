package com.appointment.controller;

import com.appointment.dto.request.*;
import com.appointment.dto.response.*;
import com.appointment.entity.SaProductCategory;
import com.appointment.service.OpportunityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @PostMapping("/create")
    public ResponseEntity<OpportunityListResponse> createOpportunity(
            @Valid @RequestBody CreateOpportunityRequest request) {
        OpportunityListResponse response = opportunityService.createOpportunity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<OpportunityListResponse>> getOpportunities(
            @Valid @RequestBody GetOpportunitiesRequest request,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<OpportunityListResponse> opportunities = opportunityService.getOpportunities(request, pageable);
        return ResponseEntity.ok(opportunities);
    }

    @PostMapping("/detail")
    public ResponseEntity<OpportunityDetailResponse> getOpportunityDetail(
            @Valid @RequestBody GetOpportunityDetailRequest request) {
        OpportunityDetailResponse response = opportunityService.getOpportunityDetail(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/by-category")
    public ResponseEntity<List<OpportunityListResponse>> getOpportunitiesByCategory(
            @Valid @RequestBody GetOpportunitiesByCategoryRequest request) {
        List<OpportunityListResponse> opportunities = opportunityService.getOpportunitiesByCategory(request);
        return ResponseEntity.ok(opportunities);
    }

    @PostMapping("/categories")
    public ResponseEntity<List<SaProductCategory>> getOpportunityCategories() {
        List<SaProductCategory> categories = opportunityService.getOpportunityCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/advance-stage")
    public ResponseEntity<String> advanceOpportunityStage(
            @Valid @RequestBody AdvanceOpportunityStageRequest request) {
        try {
            opportunityService.advanceOpportunityStage(request.getOpportunityId(), request.getDescription());
            return ResponseEntity.ok("Opportunity stage advanced successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
