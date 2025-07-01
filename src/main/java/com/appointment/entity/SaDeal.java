package com.appointment.entity;

import com.appointment.enums.DealStatus;
import com.appointment.enums.GeneralStatus;
import com.appointment.enums.VisibilityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SA_DEAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaDeal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIPELINE_ID")
    private Long pipelineId;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "DEAL_VALUE", precision = 20, scale = 0)
    private BigDecimal dealValue;

    @Column(name = "PROBABILITY", precision = 10, scale = 2)
    private BigDecimal probability;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "CUST_CODE", length = 25)
    private String custCode;

    @Column(name = "CUST_FULL_NAME", length = 250)
    private String custFullName;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "CLOSE_DATE")
    private LocalDate closeDate;

    @Column(name = "POSITION")
    private Long position;

    @Column(name = "CURRENT_STAGE_ID")
    private Long currentStageId;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEAL_STATUS", length = 45)
    private DealStatus dealStatus;

    @Column(name = "CONTACT_ID")
    private Long contactId;

    @Column(name = "CONTACT_FULL_NAME", length = 250)
    private String contactFullName;

    @Column(name = "STAFF_ID")
    private Long staffId;

    @Column(name = "STAFF_NO", length = 25)
    private String staffNo;

    @Column(name = "STAFF_FULL_NAME", length = 255)
    private String staffFullName;

    @Column(name = "SALES_AVATAR", length = 500)
    private String salesAvatar;

    @Column(name = "SALES_TEAM_ID")
    private Long salesTeamId;

    @Enumerated(EnumType.STRING)
    @Column(name = "VISIBILITY_TYPE", length = 45)
    private VisibilityType visibilityType;

    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "BRANCH_POSITION")
    private Long branchPosition;

    @Column(name = "BRANCH_CODE", length = 25)
    private String branchCode;

    @Column(name = "BRANCH_NAME", length = 250)
    private String branchName;

    @Column(name = "TRANSACTION_OFFICE_ID")
    private Long transactionOfficeId;

    @Column(name = "TRANSACTION_OFFICE_TYPE", length = 25)
    private String transactionOfficeType;

    @Column(name = "TRANSACTION_OFFICE_POSITION")
    private Long transactionOfficePosition;

    @Column(name = "TRANSACTION_OFFICE_CODE", length = 25)
    private String transactionOfficeCode;

    @Column(name = "TRANSACTION_OFFICE_NAME", length = 250)
    private String transactionOfficeName;

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "STATUS", length = 2)
    private GeneralStatus status;

    @Column(name = "CUST_ID", nullable = false)
    private Long custId;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", referencedColumnName = "CUSTOMER_ID", insertable = false, updatable = false)
    private SaCustomer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PIPELINE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaPipelines pipeline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENT_STAGE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaStages currentStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaProduct product;
}
