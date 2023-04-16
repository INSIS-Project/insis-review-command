// package com.isep.acme.messaging;

// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.stereotype.Component;

// import com.isep.acme.mappers.ReviewMapper;
// import com.isep.acme.model.Review;
// import com.isep.acme.services.ReviewService;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Component
// @AllArgsConstructor
// @Slf4j
// public class ReviewConsumer {

//     // private ReviewService reviewService;
//     // private ReviewMapper reviewMapper;

//     // @RabbitListener(queues = "#{reviewCreatedQueue}")
//     // public void receiveCreatedReviewMessage(Message message) {
//     //     try {
//     //         String bodyMessage = new String(message.getBody(), "UTF-8");
//     //         log.info("Received message for created review: {}", bodyMessage);

//     //         Review review = reviewMapper.createReviewFromMessage(bodyMessage);
//     //         reviewService.createWithReview(review);

//     //         log.info("Review created successfully with ID: {} and product {}",
//     //                 review.getIdReview(), review.getProduct().getSku());

//     //     } catch (Exception e) {
//     //         log.error("Error receving message.", e.getMessage());
//     //     }
//     }

//     // @RabbitListener(queues = "#{reviewUpdatedQueue}")
//     // public void receiveUpdatedReviewMessage(Message message) {
//     //     try {
    
//     //         String bodyMessage = new String(message.getBody(), "UTF-8");
//     //         Review review = reviewMapper.createReviewDTO(bodyMessage);
            
//     //         log.info("Received message for updated review ID: {}", review.getIdReview());

//     //         reviewService.moderateReview(review.getIdReview(), review.getApprovalStatus());
//     //         log.info("Review {} updated successfully to status {}",
//     //                 review.getIdReview(),
//     //                 review.getApprovalStatus());
//     //     } catch (Exception e) {
//     //         log.error("Error receiving updated review message: {}", e.getMessage());
//     //     }
//     // }

//     // @RabbitListener(queues = "#{reviewDeletedQueue}")
//     // public void receiveDeletedReviewMessage(Message message) {
//     //     try {
//     //         String reviewId = new String(message.getBody(), "UTF-8");
//     //         log.info("Received message for deleted review ID: {}", reviewId);

//     //         if (reviewService.deleteReview(Long.parseLong(reviewId)))
//     //             log.info("Review deleted successfully with ID: {}", reviewId);

//     //     } catch (Exception e) {
//     //         log.error("Error receiving deleted product message: {}", e.getMessage());
//     //     }
//     // }
// }
