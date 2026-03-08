package com.dimitarrradev.reviewservice.review.util;


import com.dimitarrradev.reviewservice.review.dao.ReviewRepository;
import com.dimitarrradev.reviewservice.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        reviewRepository.saveAndFlush(new Review(null,
                        "Review Title",
                        "Review Description",
                        4.99,
                        1L
                )
        );
    }
}