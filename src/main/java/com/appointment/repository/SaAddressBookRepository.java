package com.appointment.repository;

import com.appointment.entity.SaAddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaAddressBookRepository extends JpaRepository<SaAddressBook, Long>, JpaSpecificationExecutor<SaAddressBook> {
    List<SaAddressBook> findByCustId(Long custId);
}
