package com.dimitarrradev.companyservice.company.service;

import com.dimitarrradev.companyservice.company.client.ReviewClient;
import com.dimitarrradev.companyservice.company.dao.CompanyRepository;
import com.dimitarrradev.companyservice.company.dto.CompanyServiceModel;
import com.dimitarrradev.companyservice.company.dto.Rating;
import com.dimitarrradev.companyservice.company.external.ReviewModel;
import com.dimitarrradev.companyservice.company.model.Company;
import com.dimitarrradev.companyservice.company.util.ToModelMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ToModelMapper mapper;
    private final ReviewClient reviewClient;

    @Retry(name = "retryGet", fallbackMethod = "getAllFallback")
    public List<CompanyServiceModel> getAll() {
        return companyRepository.findAll().stream().map(
                company -> {
                    CompanyServiceModel companyServiceModel = mapper.toCompanyServiceModel(company);
                    companyServiceModel.setReviews(getReviews(company.getId()));
                    return companyServiceModel;
                }).toList();
    }

    @CircuitBreaker(name = "reviewBreaker", fallbackMethod = "getFallback")
    public CompanyServiceModel get(Long id) {
        return companyRepository.findById(id).map(
                company -> {
                    CompanyServiceModel companyServiceModel = mapper.toCompanyServiceModel(company);
                    companyServiceModel.setReviews(getReviews(company.getId()));
                    return companyServiceModel;
                }).orElse(null);
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

    private List<ReviewModel> getReviews(Long companyId) {
        return reviewClient.getReviews(companyId);
    }

    private List<CompanyServiceModel> getAllFallback(Exception e) {
        return companyRepository
                .findAll()
                .stream()
                .map(mapper::toCompanyServiceModel)
                .toList();
    }

    private CompanyServiceModel getFallback(Long id, Exception e) {
        return companyRepository
                .findById(id)
                .map(mapper::toCompanyServiceModel)
                .orElse(null);
    }

    public void update(Rating rating) {
        Optional<Company> byId = companyRepository.findById(rating.companyId());

        System.out.println();
        if (byId.isPresent()) {
            Company company = byId.get();
            System.out.println();
            company.setAverageRating(rating.value());
            companyRepository.saveAndFlush(company);
        }

    }
}
