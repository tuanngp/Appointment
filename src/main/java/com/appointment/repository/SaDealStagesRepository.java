package com.appointment.repository;

import com.appointment.entity.SaDealStages;
import com.appointment.enums.GeneralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaDealStagesRepository extends JpaRepository<SaDealStages, Long>, JpaSpecificationExecutor<SaDealStages> {
    
    List<SaDealStages> findByDealId(Long dealId);
    
    List<SaDealStages> findByDealIdOrderByBeginDateAsc(Long dealId);
    
    List<SaDealStages> findByDealIdAndStatus(Long dealId, GeneralStatus status);
    
    Optional<SaDealStages> findByDealIdAndStageId(Long dealId, Long stageId);
    
    @Query("SELECT ds FROM SaDealStages ds WHERE ds.dealId = :dealId ORDER BY ds.beginDate DESC LIMIT 1")
    Optional<SaDealStages> findLatestByDealId(@Param("dealId") Long dealId);
}
