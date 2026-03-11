package com.dimitarrradev.reviewservice.review.dto;

public record ReviewMessage(
        Double rating,
        Long companyId
) {
}
