package com.appointment.service;

import com.appointment.dto.request.*;
import com.appointment.dto.response.*;
import com.appointment.entity.*;
import com.appointment.enums.DealStatus;
import com.appointment.enums.GeneralStatus;
import com.appointment.exception.ResourceNotFoundException;
import com.appointment.mapper.OpportunityMapper;
import com.appointment.repository.*;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OpportunityService {

    private final SaDealRepository dealRepository;
    private final SaCustomerRepository customerRepository;
    private final SaProductRepository productRepository;
    private final SaProductCategoryRepository productCategoryRepository;
    private final SaDealStagesRepository dealStagesRepository;
    private final SaAppointmentRepository appointmentRepository;
    private final OpportunityMapper opportunityMapper;

    public OpportunityService(SaDealRepository dealRepository,
                            SaCustomerRepository customerRepository,
                            SaProductRepository productRepository,
                            SaProductCategoryRepository productCategoryRepository,
                            SaDealStagesRepository dealStagesRepository,
                            SaAppointmentRepository appointmentRepository,
                            OpportunityMapper opportunityMapper) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.dealStagesRepository = dealStagesRepository;
        this.appointmentRepository = appointmentRepository;
        this.opportunityMapper = opportunityMapper;
    }

    @Transactional
    public OpportunityListResponse createOpportunity(CreateOpportunityRequest request) {
        // Validate customer exists
        log.info("Creating opportunity for customer ID: {}", request.getCustomerId());
        SaCustomer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + request.getCustomerId()));

        log.info("Customer found: {}", customer);
        // Validate product exists
        SaProduct product = productRepository.findById(request.getInterestedProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getInterestedProductId()));
        log.info("Product found: {}", product);

        // Create SaDeal
        SaDeal deal = opportunityMapper.toSaDeal(request, customer, product);
        deal.setCreatedAt(LocalDateTime.now());
        deal.setCreatedBy(1L); // Replace with actual user ID from security context
        deal = dealRepository.save(deal);

        // Create SaAppointment if appointment datetime is provided
        if (request.getAppointmentDateTime() != null) {
            SaAppointment appointment = opportunityMapper.toSaAppointment(request, deal, customer);
            appointment.setCreatedAt(LocalDateTime.now());
            appointment.setCreatedBy(1L); // Replace with actual user ID
            appointmentRepository.save(appointment);
        }

        // Create initial deal stage (Tiếp cận)
        // Note: This assumes stage with name "Tiếp cận" exists in SA_STAGES table
        // You may need to create these stages first or handle differently

        return opportunityMapper.toOpportunityListResponse(deal);
    }

    public Page<OpportunityListResponse> getOpportunities(GetOpportunitiesRequest request, Pageable pageable) {
        Specification<SaDeal> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add fetch join for product and customer to avoid lazy loading issues
            if (query.getResultType() != Long.class) {
                root.fetch("product", jakarta.persistence.criteria.JoinType.LEFT);
                root.fetch("customer", jakarta.persistence.criteria.JoinType.LEFT);
            }

            // Filter by category code
            if (request.getCategoryCode() != null && !request.getCategoryCode().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("categoryCode"), request.getCategoryCode()));
            }

            // Filter by customer ID
            if (request.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("custId"), request.getCustomerId()));
            }

            // Filter by date range
            if (request.getFromDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), request.getFromDate().atStartOfDay()));
            }
            if (request.getToDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), request.getToDate().atTime(23, 59, 59)));
            }

            // Filter by customer name
            if (request.getCustomerName() != null && !request.getCustomerName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("custFullName")),
                        "%" + request.getCustomerName().toLowerCase() + "%"
                ));
            }

            // Filter by deal status
            if (request.getDealStatus() != null && !request.getDealStatus().isEmpty()) {
                try {
                    DealStatus status = DealStatus.valueOf(request.getDealStatus());
                    predicates.add(criteriaBuilder.equal(root.get("dealStatus"), status));
                } catch (IllegalArgumentException e) {
                    // Invalid status, ignore filter
                }
            }

            // Only active deals
            predicates.add(criteriaBuilder.equal(root.get("status"), GeneralStatus.A));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<SaDeal> deals = dealRepository.findAll(spec, pageable);
        return deals.map(deal -> {
            OpportunityListResponse response = opportunityMapper.toOpportunityListResponse(deal);
            // Set contact phone from appointment if available
            SaAppointment appointment = appointmentRepository.findByDealId(deal.getId()).stream().findFirst().orElse(null);
            if (appointment != null && appointment.getContactMobile() != null) {
                response.setContactPhone(appointment.getContactMobile());
            } else if (deal.getCustomer() != null && deal.getCustomer().getMobilePhone() != null) {
                response.setContactPhone(deal.getCustomer().getMobilePhone());
            }
            return response;
        });
    }

    public OpportunityDetailResponse getOpportunityDetail(GetOpportunityDetailRequest request) {
        // Get deal
        SaDeal deal = dealRepository.findById(request.getOpportunityId())
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found with ID: " + request.getOpportunityId()));

        // Get deal stages
        List<SaDealStages> dealStages = dealStagesRepository.findByDealIdOrderByBeginDateAsc(deal.getId());

        // Get appointment
        SaAppointment appointment = appointmentRepository.findByDealId(deal.getId()).stream().findFirst().orElse(null);

        return opportunityMapper.toOpportunityDetailResponse(deal, dealStages, appointment);
    }

    public List<OpportunityListResponse> getOpportunitiesByCategory(GetOpportunitiesByCategoryRequest request) {
        Specification<SaDeal> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add fetch join for product and customer to avoid lazy loading issues
            if (query.getResultType() != Long.class) {
                root.fetch("product", jakarta.persistence.criteria.JoinType.LEFT);
                root.fetch("customer", jakarta.persistence.criteria.JoinType.LEFT);
            }

            // Filter by category code
            predicates.add(criteriaBuilder.equal(root.get("product").get("categoryCode"), request.getCategoryCode()));

            // Filter by customer ID if provided
            if (request.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("custId"), request.getCustomerId()));
            }

            // Filter by deal status if provided
            if (request.getDealStatus() != null && !request.getDealStatus().isEmpty()) {
                try {
                    DealStatus status = DealStatus.valueOf(request.getDealStatus());
                    predicates.add(criteriaBuilder.equal(root.get("dealStatus"), status));
                } catch (IllegalArgumentException e) {
                    // Invalid status, ignore filter
                }
            }

            // Only active deals
            predicates.add(criteriaBuilder.equal(root.get("status"), GeneralStatus.A));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<SaDeal> deals = dealRepository.findAll(spec);
        return deals.stream()
                .map(deal -> {
                    OpportunityListResponse response = opportunityMapper.toOpportunityListResponse(deal);
                    // Set contact phone from appointment if available
                    SaAppointment appointment = appointmentRepository.findByDealId(deal.getId()).stream().findFirst().orElse(null);
                    if (appointment != null && appointment.getContactMobile() != null) {
                        response.setContactPhone(appointment.getContactMobile());
                    } else if (deal.getCustomer() != null && deal.getCustomer().getMobilePhone() != null) {
                        response.setContactPhone(deal.getCustomer().getMobilePhone());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<SaProductCategory> getOpportunityCategories() {
        List<String> opportunityCategoryCodes = List.of("INSURANCE", "CARD_SERVICE", "SAVINGS", "LOAN");
        return productCategoryRepository.findByCategoryCodeInAndStatus(opportunityCategoryCodes, GeneralStatus.A);
    }
}
