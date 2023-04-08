package com.isep.acme.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.services.ReviewService;

@Tag(name = "Review", description = "Endpoints for managing Review")
@RestController
class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Operation(summary = "creates review")
	@PostMapping("/products/{sku}/reviews")
	public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "sku") final String sku,
			@RequestBody CreateReviewDTO createReviewDTO) {

		final var review = reviewService.create(createReviewDTO, sku);

		if (review == null) {
			return ResponseEntity.badRequest().build();
		}

		return new ResponseEntity<ReviewDTO>(review, HttpStatus.CREATED);
	}

	@Operation(summary = "deletes review")
	@DeleteMapping("/reviews/{reviewID}")
	public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) {

		Boolean rev = reviewService.DeleteReview(reviewID);

		if (rev == null)
			return ResponseEntity.notFound().build();

		if (rev == false)
			return ResponseEntity.unprocessableEntity().build();

		return ResponseEntity.ok().body(rev);
	}

	@Operation(summary = "Accept or reject review")
	@PutMapping("/reviews/acceptreject/{reviewID}")
	public ResponseEntity<ReviewDTO> putAcceptRejectReview(@PathVariable(value = "reviewID") final Long reviewID,
			@RequestBody String approved) {

		try {
			ReviewDTO rev = reviewService.moderateReview(reviewID, approved);

			return ResponseEntity.ok().body(rev);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
