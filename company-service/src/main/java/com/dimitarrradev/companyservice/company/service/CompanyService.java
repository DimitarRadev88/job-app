package com.dimitarrradev.companyservice.company.service;

import com.dimitarrradev.companyservice.company.dao.CompanyRepository;
import com.dimitarrradev.companyservice.company.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company get(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public boolean update(Long id, Company updateCompany) {
        Optional<Company> byId = companyRepository.findById(id);

        if (byId.isEmpty()) {
            return false;
        }

        Company company = byId.get();

        company.setName(updateCompany.getName());
        company.setDescription(updateCompany.getDescription());
        if (updateCompany.getJobsIds() != null) {
            company.getJobsIds().addAll(updateCompany.getJobsIds());
        }
        if (updateCompany.getReviewsIds() != null) {
            company.getReviewsIds().addAll(updateCompany.getReviewsIds());
        }
        companyRepository.saveAndFlush(company);

        return true;
    }

    public void create(Company company) {
        company.setJobsIds(new LinkedHashSet<>());
        company.setReviewsIds(new LinkedHashSet<>());

        companyRepository.saveAndFlush(company);
    }

    public boolean delete(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            return false;
        }

        companyRepository.delete(company.get());

        return true;
    }

}
