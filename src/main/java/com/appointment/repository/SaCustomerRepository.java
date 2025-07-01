package com.appointment.repository;

import com.appointment.entity.SaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaCustomerRepository extends JpaRepository<SaCustomer, Long>, JpaSpecificationExecutor<SaCustomer> {
}
