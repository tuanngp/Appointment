package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SA_CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaCustomer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_NO", length = 9)
    private String customerNo;

    @Column(name = "CIF_NO", length = 9)
    private String cifNo;

    @Column(name = "CUST_CLASS_ID")
    private Integer custClassId;

    @Column(name = "FIRST_NAME", length = 100)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 100)
    private String middleName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "FULL_NAME", length = 255)
    private String fullName;

    @Column(name = "AVATAR", length = 500)
    private String avatar;

    @Column(name = "CUST_TYPE")
    private Integer custType;

    @Column(name = "BUSINESS", length = 100)
    private String business;

    @Column(name = "PLACE_ID")
    private Long placeId;

    @Column(name = "ADDRESS_LINE", length = 100)
    private String addressLine;

    @Column(name = "FULL_ADDRESS", length = 300)
    private String fullAddress;

    @Column(name = "HOME_TOWN", length = 200)
    private String homeTown;

    @Column(name = "MOBILE_PHONE", length = 20)
    private String mobilePhone;

    @Column(name = "MOBILE_PHONE_2", length = 20)
    private String mobilePhone2;

    @Column(name = "MOBILE_PHONE_3", length = 20)
    private String mobilePhone3;

    @Column(name = "HOME_PHONE", length = 20)
    private String homePhone;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "EMAIL_2", length = 100)
    private String email2;

    @Column(name = "EMAIL_3", length = 100)
    private String email3;

    @Column(name = "ZALO", length = 250)
    private String zalo;

    @Column(name = "FACEBOOK", length = 250)
    private String facebook;

    @Column(name = "VIBER", length = 250)
    private String viber;

    @Column(name = "WHATAPS", length = 250)
    private String whataps;

    @Column(name = "MARITAL_STATUS", length = 1)
    private String maritalStatus;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "NATIONALITY_ID")
    private Long nationalityId;

    @Column(name = "INCOME", length = 30)
    private String income;

    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "BRANCH_CODE", length = 30)
    private String branchCode;

    @Column(name = "IDENTIFY_ID")
    private Long identifyId;

    @Column(name = "IDENTIFY_TYPE", length = 20)
    private String identifyType;

    @Column(name = "IDENTIFY_BY", length = 100)
    private String identifyBy;

    @Column(name = "IDENTIFY_CHANNEL", length = 20)
    private String identifyChannel;

    @Column(name = "UNIQUE_ID")
    private Integer uniqueId;

    @Column(name = "UNIQUE_VALUE", length = 20)
    private String uniqueValue;

    @Column(name = "DATE_OF_ISSUE")
    private LocalDate dateOfIssue;

    @Column(name = "PLACE_OF_ISSUE", length = 255)
    private String placeOfIssue;

    @Column(name = "AUTH_STAT", length = 1)
    private String authStat;

    @Column(name = "MOD_NO")
    private Integer modNo;

    @Column(name = "LONGITUDE", precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "LATITUDE", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "SIGN1", length = 300)
    private String sign1;

    @Column(name = "SIGN2", length = 300)
    private String sign2;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "END_BY", length = 100)
    private String endBy;

    @Column(name = "TRANS_STATUS")
    private Integer transStatus;

    @Column(name = "APPROVE_STATUS", length = 1)
    private String approveStatus;

    @Column(name = "CUSTOMER_STATUS", length = 1)
    private String customerStatus;

    @Column(name = "QUIT_REASON", length = 45)
    private String quitReason;

    @Column(name = "INIT_DATE")
    private LocalDate initDate;

    @Column(name = "MAKER_TYPE", length = 5)
    private String makerType;

    @Column(name = "MAKER_ID", length = 100)
    private String makerId;

    @Column(name = "MAKER_DATE")
    private LocalDate makerDate;

    @Column(name = "CHECKER_TYPE", length = 1)
    private String checkerType;

    @Column(name = "CHECKER_ID", length = 100)
    private String checkerId;

    @Column(name = "CHECKER_DATE")
    private LocalDate checkerDate;

    @Column(name = "IS_LEAD", length = 25)
    private String isLead;

    @Column(name = "IS_MERGED", length = 25)
    private String isMerged;

    @Column(name = "MERGE_CUSTOMER_ID")
    private Long mergeCustomerId;

    @Column(name = "MERGE_CUSTOMER_CODE", length = 25)
    private String mergeCustomerCode;

    @Column(name = "MERGE_CUSTOMER_NAME", length = 250)
    private String mergeCustomerName;

    @Column(name = "STAFF_ID")
    private Long staffId;

    @Column(name = "STAFF_NO", length = 25)
    private String staffNo;

    @Column(name = "UNIQUE_IMAGE_FRONT", length = 300)
    private String uniqueImageFront;

    @Column(name = "UNIQUE_IMAGE_BACK", length = 300)
    private String uniqueImageBack;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "PARENT_NO", length = 30)
    private String parentNo;

    @Column(name = "ROOT_PARENT_NO", length = 30)
    private String rootParentNo;

    @Column(name = "PARTNER_CODE", length = 50)
    private String partnerCode;

    @Column(name = "INVITE_BY", length = 30)
    private String inviteBy;

    @Column(name = "REGISTER_BY", length = 30)
    private String registerBy;

    @Column(name = "STATUS", length = 1)
    private GeneralStatus status;

    @Column(name = "ASSETS_IDS", length = 100)
    private String assetsIds;

    @Column(name = "PREFERRED_TOPIC_IDS", length = 200)
    private String preferredTopicIds;

    @Column(name = "MB_USER_ID")
    private Long mbUserId;

    @Column(name = "CUSTOMER_JOB", length = 100)
    private String customerJob;

    @Column(name = "EXDATE_OF_ISSUE", nullable = false)
    private LocalDate exdateOfIssue;
}
