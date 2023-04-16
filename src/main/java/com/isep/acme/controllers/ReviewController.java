package com.isep.acme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.messaging.ReviewProducer;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.services.ProductService;
import com.isep.acme.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Review", description = "Endpoints for managing Review")
@RestController
@AllArgsConstructor
@Slf4j
class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewProducer reviewProducer;


    @Operation(summary = "creates review")
    @PostMapping("/products/{sku}/reviews")
    public ResponseEntity<Review> createReview(@PathVariable(value = "sku") final String sku,
            @RequestBody CreateReviewDTO createReviewDTO) {

        Review review = ReviewMapper.toReview(createReviewDTO);
        Product product = productService
                .findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("sku"));

        reviewService.create(review, product);
        log.info("Review created successfully with ID: {} and product {}",
                review.getIdReview(), review.getProduct().getSku());

        reviewProducer.sendCreatedReviewMessage(review);

        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @Operation(summary = "deletes review")
    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) {

        Boolean rev = reviewService.deleteReview(reviewID);

        if (rev == null)
            return ResponseEntity.notFound().build();

        if (rev == false)
            return ResponseEntity.badRequest().build()  ;
        
        reviewProducer.sendDeletedReviewMessage(reviewID);
        return ResponseEntity.ok().body(rev);
    }

    @Operation(summary = "Accept or reject review")
    @PatchMapping("/reviews/acceptreject/{reviewID}")
    public ResponseEntity<ReviewDTO> putAcceptRejectReview(@PathVariable(value = "reviewID") final Long reviewID,
            @RequestBody String status) {
            Review review = reviewService.moderateReview(reviewID, status);

            log.info("Sending message to update review {} and product {}", 
                                                            review.getIdReview(), 
                                                            review.getProduct().getSku());
            ReviewDTO reviewDTO = ReviewMapper.toDto(review);
            System.out.println(reviewDTO.getVotes());
            reviewProducer.sendUpdatedReviewMessage(reviewDTO);
            
            return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.CREATED);
     }
}
