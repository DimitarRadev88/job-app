package com.dimitarrradev.companyservice.company.external;

public record ReviewModel(
        Long id,
        String title,
        String description,
        Double rating
) {
}
