package com.dimitarrradev.companyservice.company.service;

import com.dimitarrradev.companyservice.company.dto.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageService {

    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    void consumeMessage(Rating rating) {
        companyService.update(rating);
    }

}
