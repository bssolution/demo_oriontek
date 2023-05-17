package com.oriontek.demo.service;

import java.util.List;

import com.oriontek.demo.dto.CompanyDTO;
import com.oriontek.demo.model.Company;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    List<Company> findAll();
    Company findById(Long companyId);
    CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO);
    void deleteCompany(Long companyId);
}