package com.appointment.entity;

import com.appointment.enums.CustRemindType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SA_CUST_REMIND")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaCustRemind extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "REMINDER_B4_DUE")
    private Integer reminderB4Due;

    @Column(name = "REMINDER_INTERVAL")
    private Integer reminderInterval;

    @Column(name = "CUST_ID")
    private Long custId;

    @Column(name = "CUST_NO", length = 25)
    private String custNo;

    @Column(name = "CUST_FULL_NAME", length = 250)
    private String custFullName;

    @Column(name = "PAYMENT_VALUE")
    private BigDecimal paymentValue; // number(20,0)

    @Column(name = "STATUS", length = 1)
    private CustRemindType status;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALENDAR_ID", referencedColumnName = "ID")
    private SaCalendar calendar;
}