package com.oriontek.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oriontek.demo.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByCustomerId(Long customerId);
}