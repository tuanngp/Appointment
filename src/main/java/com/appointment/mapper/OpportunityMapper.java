package com.appointment.mapper;

import com.appointment.dto.request.CreateOpportunityRequest;
import com.appointment.dto.response.*;
import com.appointment.entity.*;
import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.DealStatus;
import com.appointment.enums.GeneralStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class OpportunityMapper {

    /**
     * Map CreateOpportunityRequest to SaDeal entity
     */
    public SaDeal toSaDeal(CreateOpportunityRequest request, SaCustomer customer, SaProduct product) {
        if (request == null) {
            return null;
        }

        SaDeal deal = new SaDeal();
        deal.setName(request.getOpportunityName());
        deal.setProductId(request.getInterestedProductId());
        deal.setCustId(request.getCustomerId());
        deal.setCustFullName(customer != null ? customer.getFullName() : null);
        deal.setCustCode(customer != null ? customer.getCustomerNo() : null);
        deal.setContactFullName(customer != null ? customer.getFullName() : null);
        deal.setStartDate(LocalDate.now());
        deal.setDealStatus(DealStatus.W); // Won
        deal.setStatus(GeneralStatus.A); // Active
        deal.setDescription(request.getNote());
        deal.setCustomer(customer);
        // Set product info if available
        if (product != null) {
            deal.setProduct(product);
        }

        return deal;
    }

    /**
     * Map CreateOpportunityRequest to SaAppointment entity
     */
    public SaAppointment toSaAppointment(CreateOpportunityRequest request, SaDeal deal, SaCustomer customer) {
        if (request == null || request.getAppointmentDateTime() == null) {
            return null;
        }

        SaAppointment appointment = new SaAppointment();
        appointment.setTitle("Appointment for " + request.getOpportunityName());
        appointment.setNote(request.getNote());
        appointment.setPriority(request.getPriority() != null ? request.getPriority() : 1);
        appointment.setDueDateTime(request.getAppointmentDateTime());
        appointment.setDurationMin(request.getDurationMin() != null ? request.getDurationMin() : 60L);
        appointment.setContactMobile(request.getPhoneNumber());
        appointment.setStatus(AppointmentStatus.A); // Active
        
        // Set customer info
        if (customer != null) {
            appointment.setCustomer(customer);
            appointment.setCustNo(customer.getCustomerNo());
            appointment.setCustFullName(customer.getFullName());
            appointment.setContactFullName(customer.getFullName());
        }
        
        // Set deal info
        if (deal != null) {
            appointment.setDealId(deal.getId());
            appointment.setDealName(deal.getName());
            appointment.setDealValue(deal.getDealValue());
        }

        return appointment;
    }

    /**
     * Map SaDeal to OpportunityListResponse
     */
    public OpportunityListResponse toOpportunityListResponse(SaDeal deal) {
        if (deal == null) {
            return null;
        }

        OpportunityListResponse response = new OpportunityListResponse();
        response.setId(deal.getId());
        response.setOpportunityName(deal.getName());
        response.setCreatedDate(deal.getCreatedAt());
        // Set contact phone - we need to get this from customer or deal
        // For now, we'll try to get it from related appointment or customer
        response.setContactPhone(null); // Will be set by service if needed
        response.setCategoryName(deal.getProduct() != null ? deal.getProduct().getCategoryName() : null);
        response.setCustomerName(deal.getCustFullName());
        response.setDealStatus(deal.getDealStatus() != null ? deal.getDealStatus().name() : null);
        
        // Set email from customer if available
        if (deal.getCustomer() != null) {
            response.setEmail(deal.getCustomer().getEmail());
            response.setContactPhone(deal.getCustomer().getMobilePhone());
        }

        return response;
    }

    /**
     * Map SaDeal to OpportunityDetailResponse
     */
    public OpportunityDetailResponse toOpportunityDetailResponse(SaDeal deal, List<SaDealStages> dealStages,
                                                               SaAppointment appointment) {
        if (deal == null) {
            return null;
        }

        OpportunityDetailResponse response = new OpportunityDetailResponse();

        // Set opportunity info
        response.setOpportunityInfo(toOpportunityInfo(deal));

        // Set stage status
        response.setStageStatus(toOpportunityStageStatus(dealStages));

        // Set appointment info
        response.setAppointmentInfo(toAppointmentInfo(appointment));

        // Set contract info
        response.setContractInfo(toContractInfo(deal));

        return response;
    }

    /**
     * Map SaDeal to OpportunityInfo
     */
    public OpportunityInfo toOpportunityInfo(SaDeal deal) {
        if (deal == null) {
            return null;
        }

        OpportunityInfo info = new OpportunityInfo();
        info.setId(deal.getId());
        info.setOpportunityName(deal.getName());
        info.setOpportunityType(deal.getProduct() != null ? deal.getProduct().getCategoryName() : null);
        info.setCustomerName(deal.getCustFullName());
        info.setDescription(deal.getDescription());
        info.setDealStatus(deal.getDealStatus() != null ? deal.getDealStatus().name() : null);

        // Set phone number from customer if available
        if (deal.getCustomer() != null && deal.getCustomer().getMobilePhone() != null) {
            info.setPhoneNumber(deal.getCustomer().getMobilePhone());
        }

        // Set product name if available
        if (deal.getProduct() != null) {
            info.setProductName(deal.getProduct().getProductName());
        }

        return info;
    }

    /**
     * Map List<SaDealStages> to OpportunityStageStatus
     */
    public OpportunityStageStatus toOpportunityStageStatus(List<SaDealStages> dealStages) {
        if (dealStages == null || dealStages.isEmpty()) {
            return new OpportunityStageStatus();
        }

        OpportunityStageStatus stageStatus = new OpportunityStageStatus();

        for (SaDealStages stage : dealStages) {
            if (stage.getStage() != null) {
                String stageName = stage.getStage().getName();
                LocalDateTime beginDate = stage.getBeginDate();

                if ("Tiếp cận".equals(stageName)) {
                    stageStatus.setApproachDate(beginDate);
                } else if ("Tạo hồ sơ".equals(stageName)) {
                    stageStatus.setProfileCreationDate(beginDate);
                } else if ("Chờ duyệt".equals(stageName)) {
                    stageStatus.setApprovalWaitingDate(beginDate);
                } else if ("Kết quả hồ sơ".equals(stageName)) {
                    stageStatus.setResultDate(beginDate);
                }
            }
        }

        return stageStatus;
    }

    /**
     * Map SaAppointment to AppointmentInfo
     */
    public AppointmentInfo toAppointmentInfo(SaAppointment appointment) {
        if (appointment == null) {
            return null;
        }

        AppointmentInfo info = new AppointmentInfo();
        info.setAppointmentId(appointment.getId());
        info.setTitle(appointment.getTitle());
        info.setAppointmentDateTime(appointment.getDueDateTime());
        info.setNote(appointment.getNote());
        info.setStatus(appointment.getStatus() != null ? appointment.getStatus().name() : null);
        info.setDurationMin(appointment.getDurationMin());
        info.setContactMobile(appointment.getContactMobile());

        return info;
    }

    /**
     * Map SaDeal to ContractInfo
     */
    public ContractInfo toContractInfo(SaDeal deal) {
        if (deal == null) {
            return null;
        }

        ContractInfo info = new ContractInfo();
        info.setContractNumber(deal.getContractId() != null ? deal.getContractId().toString() : null);
        info.setTotalFee(deal.getDealValue());
        info.setStatus(deal.getDealStatus() != null ? deal.getDealStatus().name() : null);
        info.setDescription(deal.getDescription());

        return info;
    }
}
