package com.oriontek.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oriontek.demo.dto.CompanyDTO;
import com.oriontek.demo.model.Company;
import com.oriontek.demo.repository.CompanyRepository;
import com.oriontek.demo.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        Company savedCompany = companyRepository.save(company);
        return mapCompanyToDTO(savedCompany);
    }

    @Override
    public CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));

        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());

        Company updatedCompany = companyRepository.save(company);
        return mapCompanyToDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    private CompanyDTO mapCompanyToDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setAddress(company.getAddress());
        return companyDTO;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @Override
    public Company findById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));
        return company;
    }

}
