package com.dimitarrradev.jobservice.job.dto;

import com.dimitarrradev.jobservice.job.external.CompanyModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobServiceModel {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private CompanyModel company;
}
