package com.dimitarrradev.jobservice.job.external;

import java.util.Set;

public record CompanyModel (
        Long id,
        String name,
        String description,
        Set<Long>jobsIds,
        Set<Long> reviewsIds
) {
}
