package com.dimitarrradev.jobservice.job.external;

public record ReviewModel(
        Long id,
        String title,
        String description,
        Double rating
) {
}
