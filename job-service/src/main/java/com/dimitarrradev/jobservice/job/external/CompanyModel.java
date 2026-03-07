package com.dimitarrradev.jobservice.job.external;

import java.util.List;

public record CompanyModel(
        Long id,
        String name,
        String description,
        List<ReviewModel> reviews
) {
}
