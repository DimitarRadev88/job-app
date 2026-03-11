package com.dimitarrradev.companyservice.company.dto;

import com.dimitarrradev.companyservice.company.external.ReviewModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyServiceModel {
    private Long id;
    private String name;
    private String description;
    private Double averageRating;
    private List<ReviewModel> reviews;
}
