package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "SA_ADDRESS_BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaAddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 250)
    private String title;

    @Column(name = "PLACE_ID", nullable = false)
    private Long placeId;

    @Column(name = "ADDRESS_LINE", length = 100)
    private String addressLine;

    @Column(name = "FULL_ADDRESS", length = 1000)
    private String fullAddress;

    @Column(name = "LONGITUDE")
    private Long longitude;

    @Column(name = "LATITUDE")
    private Long latitude;

    @Column(name = "ORDER_NO")
    private Integer orderNo;

    @Column(name = "STATUS", length = 1)
    private GeneralStatus status;

    @Column(name = "CUST_ID")
    private Long custId;

    // Relationship with SaCustomer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", referencedColumnName = "CUSTOMER_ID", insertable = false, updatable = false)
    private SaCustomer customer;
}
