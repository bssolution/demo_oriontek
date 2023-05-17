package com.oriontek.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oriontek.demo.model.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  
}