package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SA_PRODUCT_CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaProductCategory {

    @Id
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_CODE", length = 200, nullable = false)
    private String categoryCode;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "CATEGORY_NAME", length = 300)
    private String categoryName;

    @Column(name = "CATEGORY_SHORT_NAME", length = 300)
    private String categoryShortName;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "ICON", length = 300)
    private String icon;

    @Column(name = "EFFECT_DATE")
    private LocalDate effectDate;

    @Column(name = "EXPIRE_DATE")
    private LocalDate expireDate;

    @Column(name = "ORDER_NO")
    private Integer orderNo;

    @Column(name = "CATEGORY_LEVEL")
    private Integer categoryLevel;

    @Column(name = "IS_LEAF", length = 1, nullable = false)
    private String isLeaf;

    @Column(name = "STATUS", length = 1)
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
}
