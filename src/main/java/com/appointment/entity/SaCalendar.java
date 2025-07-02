package com.appointment.entity;

import com.appointment.enums.CalendarType;
import com.appointment.enums.CalenderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SA_CALENDAR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaCalendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", length = 10)
    private CalendarType type;

    @Column(name = "OBJECT_ID")
    private Long objectId;

    @Column(name = "TITLE", length = 250)
    private String title;

    @Column(name = "PROD_NOTE", length = 500)
    private String prodNote;

    @Column(name = "ADDRESS_NOTE", length = 500)
    private String addressNote;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;

    @Column(name = "ASSIGNED_STAFF_ID")
    private Long assignedStaffId;

    @Column(name = "ASSIGNED_STAFF_NO", length = 45)
    private String assignedStaffNo;

    @Column(name = "FOLLOWED_STAFF_ID")
    private Long followedStaffId;

    @Column(name = "FOLLOWED_STAFF_NO", length = 45)
    private String followedStaffNo;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private CalenderStatus status;

    @Column(name = "CUST_ID")
    private Long custId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", referencedColumnName = "CUSTOMER_ID", insertable = false, updatable = false)
    private SaCustomer customer;
}