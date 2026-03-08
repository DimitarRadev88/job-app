package com.dimitarrradev.companyservice.company.client;

import com.dimitarrradev.companyservice.company.external.ReviewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<ReviewModel> getReviews(@RequestParam Long companyId);

}
