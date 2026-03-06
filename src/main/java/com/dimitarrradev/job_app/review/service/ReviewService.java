package com.dimitarrradev.job_app.review.service;

import com.dimitarrradev.job_app.company.dao.CompanyRepository;
import com.dimitarrradev.job_app.company.model.Company;
import com.dimitarrradev.job_app.review.dao.ReviewRepository;
import com.dimitarrradev.job_app.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public List<Review> getAll(Long companyId) {
        return reviewRepository.findAllByCompany_id(companyId);
    }

    public boolean create(Long id, Review review) {
        Optional<Company> byId = companyRepository.findById(id);

        if (byId.isPresent()) {
            Company company = byId.get();
            review.setCompany(company);
            reviewRepository.saveAndFlush(review);
            return true;
        }

        return false;
    }

    public Review get(Long companyId, Long reviewId) {
        return reviewRepository.findByIdAndCompany_id(reviewId, companyId).orElse(null);
    }

    public boolean update(Long companyId, Long reviewId, Review updateReview) {
        Optional<Review> optionalReview = reviewRepository.findByIdAndCompany_id(reviewId, companyId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());

            reviewRepository.saveAndFlush(review);
        }

        return false;
    }

    public boolean delete(Long companyId, Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findByIdAndCompany_id(reviewId, companyId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();

            reviewRepository.delete(review);
            return true;
        }

        return false;
    }
}
