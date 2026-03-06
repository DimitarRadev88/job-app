package com.dimitarrradev.reviewservice.review.web;

import com.dimitarrradev.reviewservice.review.model.Review;
import com.dimitarrradev.reviewservice.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<List<Review>> getAll(@RequestParam Long companyId) {
        return ResponseEntity
                .ok(reviewService.getAll(companyId));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody Review review) {
        boolean isCreated = reviewService.create(review);

        if (isCreated) {
            return ResponseEntity
                    .ok("Successfully created review");
        }

        return ResponseEntity.ok("Review not created");
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> get(@PathVariable Long reviewId) {
        Review review = reviewService.get(reviewId);

        if (review == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok(review);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<String> update(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = reviewService.update(reviewId, review);

        if (isUpdated) {
            return ResponseEntity
                    .ok("Review updated successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long reviewId) {
        boolean isDeleted = reviewService.delete(reviewId);

        if (isDeleted) {
            return ResponseEntity
                    .ok("Review deleted successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

}
