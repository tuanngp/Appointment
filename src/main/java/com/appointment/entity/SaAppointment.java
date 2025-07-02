package com.appointment.entity;

import com.appointment.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SA_APPOINTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaAppointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 250)
    private String title;

    @Column(name = "NOTE", length = 1000)
    private String note;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "DUE_DATE_TIME")
    private LocalDateTime dueDateTime;

    @Column(name = "DURATION_MIN")
    private Long durationMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", referencedColumnName = "CUSTOMER_ID")
    private SaCustomer customer;

    @Column(name = "CUST_NO", length = 25)
    private String custNo;

    @Column(name = "CUST_FULL_NAME", length = 250)
    private String custFullName;

    @Column(name = "CONTACT_TITLE", length = 19)
    private String contactTitle;

    @Column(name = "CONTACT_MOBILE", length = 19)
    private String contactMobile;

    @Column(name = "CONTACT_FULL_NAME", length = 250)
    private String contactFullName;

    @Column(name = "NUM_OF_ATTENDANCE")
    private Integer numOfAttendance;

    @Column(name = "ATTENDANCE_CONTACT_IDS", length = 500)
    private String attendanceContactIds;

    @Column(name = "DEAL_ID")
    private Long dealId;

    @Column(name = "DEAL_NAME", length = 250)
    private String dealName;

    @Column(name = "DEAL_VALUE")
    private BigDecimal dealValue;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALENDAR_ID", referencedColumnName = "ID")
    private SaCalendar calendar;

    @Column(name = "ADDR_BOOK_ID")
    private Long addrBookId;
}