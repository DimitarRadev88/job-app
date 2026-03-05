package com.dimitarrradev.job_app.web.review.web;

import com.dimitarrradev.job_app.web.review.model.Review;
import com.dimitarrradev.job_app.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<List<Review>> getAll(@PathVariable Long companyId) {
        return ResponseEntity
                .ok(reviewService.getAll(companyId));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isCreated = reviewService.create(companyId, review);

        if (isCreated) {
            return ResponseEntity
                    .ok("Successfully created review");
        }

        return ResponseEntity.ok("Review not created");
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> get(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.get(companyId, reviewId);

        if (review == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok(review);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<String> update(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = reviewService.update(companyId, reviewId, review);

        if (isUpdated) {
            return ResponseEntity
                    .ok("Review updated successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.delete(companyId, reviewId);

        if (isDeleted) {
            return ResponseEntity
                    .ok("Review deleted successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

}
