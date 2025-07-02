package com.appointment.entity;

import com.appointment.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SA_PIPELINE_STAGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaPipelineStages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIPELINE_ID")
    private Long pipelineId;

    @Column(name = "STAGE_ID")
    private Long stageId;

    @Column(name = "POSITION")
    private Long position;

    @Column(name = "STATUS", length = 2)
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PIPELINE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaPipelines pipeline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAGE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SaStages stage;
}
