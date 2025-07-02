package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SA_PIPELINES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaPipelines extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "POSITION")
    private Integer position;

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "STATUS", length = 2)
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;

    // Relationship with SaProduct
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaProduct product;
}
