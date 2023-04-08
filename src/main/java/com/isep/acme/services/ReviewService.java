package com.isep.acme.services;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;

public interface ReviewService {

	ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

	Boolean DeleteReview(Long reviewId);

	ReviewDTO moderateReview(Long reviewID, String approved);

}
