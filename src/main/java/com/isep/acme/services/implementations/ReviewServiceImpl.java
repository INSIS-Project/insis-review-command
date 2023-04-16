package com.isep.acme.services.implementations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.ProductService;
import com.isep.acme.services.RatingService;
import com.isep.acme.services.RestService;
import com.isep.acme.services.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RatingService ratingService;
    
    @Autowired
    RestService restService;
    
    @Autowired
    ProductService productService;

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Review create(final Review review, final Product product) {

        String funfact = restService.getFunFact(LocalDate.now());   

        review.setFunFact(funfact);
        review.setProduct(product);
        review.setApprovalStatus("pending");
        reviewRepository.save(review);

        return review;
    }

    @Override
    public Review createWithReview(Review review) {

        reviewRepository.findById(review.getIdReview())
                .ifPresent(existingReview -> {
                    throw new IllegalArgumentException("Review with ID " + review.getIdReview() + " already exists.");
                });

        return reviewRepository.save(review);
    }

    @Override
    public Review moderateReview(Long reviewID, String status)
            throws ResourceNotFoundException, IllegalArgumentException {

        Review reviewToUpdate = reviewRepository.findById(reviewID)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        System.out.println("Review to update: " + reviewToUpdate.getVotes());
        if (reviewToUpdate.setApprovalStatus(status))
            return reviewRepository.save(reviewToUpdate);

        return null;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {

        Review reviewToDelete = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        //not possible delet review with votes
        if (reviewToDelete.getVotes() != null){
            log.info("Review with votes can't be deleted");
            return false;
        }
   
        reviewRepository.delete(reviewToDelete);
        return true;
    }

    @Override
    public Review addVoteToReview(Long reviewID, Vote vote){
        try {
            Review review = reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review not found"));        
            System.out.println("Review: " + review.getVotes());
            review.addVote(vote);
            System.out.println("Review: " + review.getVotes());
            return reviewRepository.save(review);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error adding vote to review");
        }

    }
}