package com.isep.acme.services;

import java.util.List;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.model.*;

public interface ReviewService {

	Iterable<Review> getAll();

	List<ReviewDTO> getReviewsOfProduct(String sku, String status);

	ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

	Double getWeightedAverage(Product product);

	Boolean DeleteReview(Long reviewId);

	List<ReviewDTO> findPendingReview();

	ReviewDTO moderateReview(Long reviewID, String approved);

	List<ReviewDTO> findReviewsByUser(Long userID);
}
