package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.model.Review;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReviewProducer {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public void sendCreatedReviewMessage(Review review) {
        rabbitTemplate.convertAndSend("ms.reviews.review-created", "", review);
    }

    public void sendUpdatedReviewMessage(ReviewDTO review) {
        rabbitTemplate.convertAndSend("ms.reviews.review-updated", "", review);
    }

    public void sendDeletedReviewMessage(Long ReviewID) {
        rabbitTemplate.convertAndSend("ms.reviews.review-deleted", "", ReviewID);
    }
}
