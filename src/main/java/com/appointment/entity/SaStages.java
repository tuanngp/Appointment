package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "SA_STAGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaStages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "POSITION")
    private Long position;

    @Column(name = "PROBABILITY", precision = 20, scale = 2)
    private BigDecimal probability;

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "STATUS", length = 2)
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
}
