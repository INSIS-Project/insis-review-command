package com.isep.acme.services;

import org.springframework.stereotype.Service;

import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;

@Service
public interface ReviewService {

	Review create(Review review, Product product);
    Review createWithReview(Review review);

	Boolean deleteReview(Long reviewId);

	Review moderateReview(Long reviewID, String status);
	Review addVoteToReview(Long reviewID, Vote vote);
}
