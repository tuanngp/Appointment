package com.appointment.repository;

import com.appointment.entity.SaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SaProductRepository extends JpaRepository<SaProduct, Long>, JpaSpecificationExecutor<SaProduct> {
    Optional<SaProduct> findByProductCode(String productCode);
}
