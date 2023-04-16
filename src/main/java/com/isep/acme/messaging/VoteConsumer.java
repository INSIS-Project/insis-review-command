package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.mappers.VoteMapper;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.services.ReviewService;

import org.springframework.amqp.core.Message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class VoteConsumer {
    
    private VoteMapper voteMapper;
    private ReviewService reviewService;
    private ReviewProducer reviewProducer;

    @RabbitListener(queues = "#{voteCreatedQueue}")
    public void receiveCreatedVoteMessage(Message message) {
        try {
            String bodyMessage = new String(message.getBody(), "UTF-8");
            log.info("Received message for created vote: {}", bodyMessage);
            VoteDTO voteDTO = voteMapper.toVoteDTO(bodyMessage);
            Vote vote = voteMapper.toEntity(voteDTO);
            Review review = reviewService.addVoteToReview(voteDTO.getIdReview(), vote);
            ReviewDTO reviewDTO = ReviewMapper.toDto(review);
            log.info("Vote created successfully.");
            reviewProducer.sendUpdatedReviewMessage(reviewDTO);

        } catch (Exception e) {
            log.error("Error receving message.", e.getMessage());
        }
    }

}