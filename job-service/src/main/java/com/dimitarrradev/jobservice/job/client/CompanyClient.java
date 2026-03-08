package com.dimitarrradev.jobservice.job.client;

import com.dimitarrradev.jobservice.job.external.CompanyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    CompanyModel getCompany(@PathVariable Long id);

}
