package com.dimitarrradev.reviewservice.review.service;

import com.dimitarrradev.reviewservice.review.dto.Rating;
import com.dimitarrradev.reviewservice.review.dto.ReviewMessage;
import com.dimitarrradev.reviewservice.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Rating rating) {
        rabbitTemplate.convertAndSend("companyRatingExchange", "key", rating);
    }

}
