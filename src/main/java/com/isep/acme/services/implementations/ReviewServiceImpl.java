package com.isep.acme.services.implementations;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.dtos.ReviewDTO;
// import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.mappers.ReviewMapper;

import java.lang.IllegalArgumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.*;

import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.UserRepository;
import com.isep.acme.services.RatingService;
import com.isep.acme.services.RestService;
import com.isep.acme.services.ReviewService;
import com.isep.acme.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@Autowired
	RestService restService;

	@Override
	public Iterable<Review> getAll() {
		return reviewRepository.findAll();
	}

	@Override
	public ReviewDTO create(final CreateReviewDTO createReviewDTO, String sku) {

		final Optional<Product> product = productRepository.findBySku(sku);

		if (product.isEmpty())
			return null;

		final var user = userService.getUserId(createReviewDTO.getUserID());

		if (user.isEmpty())
			return null;

		Rating rating = null;
		Optional<Rating> r = ratingService.findByRate(createReviewDTO.getRating());
		if (r.isPresent()) {
			rating = r.get();
		}

		LocalDate date = LocalDate.now();

		String funfact = restService.getFunFact(date);

		if (funfact == null)
			return null;

		Review review = new Review(createReviewDTO.getReviewText(), date, product.get(), funfact, rating, user.get());

		review = reviewRepository.save(review);

		if (review == null)
			return null;

		return ReviewMapper.toDto(review);
	}

	@Override
	public List<ReviewDTO> getReviewsOfProduct(String sku, String status) {

		Optional<Product> product = productRepository.findBySku(sku);
		if (product.isEmpty())
			return null;

		Optional<List<Review>> r = reviewRepository.findByProductIdStatus(product.get(), status);

		if (r.isEmpty())
			return null;

		return ReviewMapper.toDtoList(r.get());
	}

	@Override
	public Double getWeightedAverage(Product product) {

		Optional<List<Review>> r = reviewRepository.findByProductId(product);

		if (r.isEmpty())
			return 0.0;

		double sum = 0;

		for (Review rev : r.get()) {
			Rating rate = rev.getRating();

			if (rate != null) {
				sum += rate.getRate();
			}
		}

		return sum / r.get().size();
	}

	@Override
	public Boolean DeleteReview(Long reviewId) {

		Optional<Review> rev = reviewRepository.findById(reviewId);

		if (rev.isEmpty()) {
			return null;
		}

		Review r = rev.get();
		reviewRepository.delete(r);

		return true;

	}

	@Override
	public List<ReviewDTO> findPendingReview() {

		Optional<List<Review>> r = reviewRepository.findPendingReviews();

		if (r.isEmpty()) {
			return null;
		}

		return ReviewMapper.toDtoList(r.get());
	}

	@Override
	public ReviewDTO moderateReview(Long reviewID, String approved)
			throws ResourceNotFoundException, IllegalArgumentException {

		Optional<Review> r = reviewRepository.findById(reviewID);

		if (r.isEmpty()) {
			throw new ResourceNotFoundException("Review not found");
		}

		Boolean ap = r.get().setApprovalStatus(approved);

		if (!ap) {
			throw new IllegalArgumentException("Invalid status value");
		}

		Review review = reviewRepository.save(r.get());

		return ReviewMapper.toDto(review);
	}

	@Override
	public List<ReviewDTO> findReviewsByUser(Long userID) {

		final Optional<User> user = userRepository.findById(userID);

		if (user.isEmpty())
			return null;

		Optional<List<Review>> r = reviewRepository.findByUserId(user.get());

		if (r.isEmpty())
			return null;

		return ReviewMapper.toDtoList(r.get());
	}
}