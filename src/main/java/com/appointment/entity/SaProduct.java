package com.appointment.entity;

import com.appointment.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SA_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_CODE", length = 25, unique = true)
    private String productCode;

    @Column(name = "PRODUCT_NAME", length = 250)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", length = 10)
    private ProductType productType;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_CODE", length = 25)
    private String categoryCode;

    @Column(name = "CATEGORY_NAME", length = 250)
    private String categoryName;

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "STATUS", length = 45)
    private String status;

    // Relationship with SaProductCategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", insertable = false, updatable = false)
    private SaProductCategory category;
}
