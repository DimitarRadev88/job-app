package com.dimitarrradev.reviewservice.review.service;

import com.dimitarrradev.reviewservice.review.dao.ReviewRepository;
import com.dimitarrradev.reviewservice.review.dto.Rating;
import com.dimitarrradev.reviewservice.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAll(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    public Optional<Rating> create(Review review) {
        Review saved = reviewRepository.saveAndFlush(review);

        Rating value = new Rating(saved.getCompanyId(), reviewRepository.calculateAverageRating(saved.getCompanyId()));
        return Optional.of(value);
    }

    public Review get(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public boolean update(Long reviewId, Review updateReview) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());

            reviewRepository.saveAndFlush(review);
            return true;
        }

        return false;
    }

    public boolean delete(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            reviewRepository.delete(review);
            return true;
        }

        return false;
    }

    public Rating getAverageRating(Long companyId) {
        return new Rating(companyId, reviewRepository.calculateAverageRating(companyId));
    }
}
