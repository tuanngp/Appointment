package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SA_DEAL_STAGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaDealStages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIPELINE_ID")
    private Long pipelineId;

    @Column(name = "STAGE_ID")
    private Long stageId;

    @Column(name = "BEGIN_DATE")
    private LocalDateTime beginDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "DURATION")
    private Long duration;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "DS_STATUS", length = 2)
    @Enumerated(EnumType.STRING)
    private GeneralStatus dsStatus;

    @Column(name = "STATUS", length = 2)
    private GeneralStatus status;

    @Column(name = "PS_ID", nullable = false)
    private Long psId;

    @Column(name = "DEAL_ID", nullable = false)
    private Long dealId;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEAL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaDeal deal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaPipelineStages pipelineStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PIPELINE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaPipelines pipeline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAGE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaStages stage;
}
