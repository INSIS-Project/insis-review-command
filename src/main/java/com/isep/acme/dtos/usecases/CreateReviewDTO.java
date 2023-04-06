package com.isep.acme.dtos.usecases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDTO {

	private String reviewText;

	private Long userID;

	private Double rating;

	public CreateReviewDTO() {
	}

	public CreateReviewDTO(String reviewText) {
		this.reviewText = reviewText;
	}

	public CreateReviewDTO(Double rating) {
		this.rating = rating;
	}

}
