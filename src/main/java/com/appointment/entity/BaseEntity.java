package com.appointment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "CREATED_BY")
    protected Long createdBy;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    protected Long updatedBy;

    @Column(name = "DELETED_BY")
    protected Long deletedBy;

    @CreatedDate
    @Column(name = "CREATED_AT")
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;

    @Column(name = "DELETED_AT")
    protected LocalDateTime deletedAt;
}