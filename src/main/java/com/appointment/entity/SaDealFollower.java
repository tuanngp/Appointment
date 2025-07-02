package com.appointment.entity;

import com.appointment.enums.FollowerType;
import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SA_DEAL_FOLLOWER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaDealFollower extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OBJECT_TYPE", length = 45)
    private String objectType;

    @Column(name = "OBJECT_ID", length = 25)
    private String objectId;

    @Column(name = "FOLLOWER_STAFF_ID")
    private Long followerStaffId;

    @Column(name = "FOLLOWER_STAFF_NO", length = 25)
    private String followerStaffNo;

    @Column(name = "FOLLOWER_FULL_NAME", length = 250)
    private String followerFullName;

    @Column(name = "FOLLOWER_AVATAR", length = 500)
    private String followerAvatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "FOLLOWER_TYPE", length = 45)
    private FollowerType followerType;

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
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;

    @Column(name = "DEAL_ID")
    private Long dealId;

    // Relationship with SaDeal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEAL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaDeal deal;
}
