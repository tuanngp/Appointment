package com.appointment.repository;

import com.appointment.entity.SaAddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaAddressBookRepository extends JpaRepository<SaAddressBook, Long>, JpaSpecificationExecutor<SaAddressBook> {
    List<SaAddressBook> findByCustomer_CustomerId(Long customerId);

    @Query("SELECT s FROM SaAddressBook s WHERE s.customer.customerId = :customerId ORDER BY s.orderNo ASC LIMIT 1")
    Optional<SaAddressBook> findFirstByCustomer_CustomerIdOrderByOrderNoAsc(@Param("customerId") Long customerId);
}
