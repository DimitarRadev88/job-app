package com.dimitarrradev.companyservice.company.util;

import com.dimitarrradev.companyservice.company.dto.CompanyServiceModel;
import com.dimitarrradev.companyservice.company.model.Company;
import org.springframework.stereotype.Component;

@Component
public class ToModelMapper {

    public CompanyServiceModel toCompanyServiceModel(Company company) {
        return new CompanyServiceModel(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getAverageRating(),
                null
        );
    }

}
