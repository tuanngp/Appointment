package com.appointment.repository;

import com.appointment.entity.SaProductCategory;
import com.appointment.enums.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SaProductCategoryRepository extends JpaRepository<SaProductCategory, Long>, JpaSpecificationExecutor<SaProductCategory> {
    
    Optional<SaProductCategory> findByCategoryCode(String categoryCode);
    
    List<SaProductCategory> findByCategoryCodeIn(List<String> categoryCodes);
    
    List<SaProductCategory> findByStatus(GeneralStatus status);
    
    List<SaProductCategory> findByCategoryCodeInAndStatus(List<String> categoryCodes, GeneralStatus status);
}
