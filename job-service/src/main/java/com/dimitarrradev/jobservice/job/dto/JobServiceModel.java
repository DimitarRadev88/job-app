package com.dimitarrradev.jobservice.job.dto;

import com.dimitarrradev.jobservice.job.external.CompanyModel;

public record JobServiceModel(
        Long id,
        String title,
        String description,
        String minSalary,
        String maxSalary,
        String location,
        CompanyModel company
) {

}
