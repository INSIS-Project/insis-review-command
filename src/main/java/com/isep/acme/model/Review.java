package com.isep.acme.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReview;

	@Version
	private long version;

	@Column(nullable = false)
	private String approvalStatus;

	@Column(nullable = false)
	private String reviewText;

	@Column(nullable = true)
	private String report;

	@Column(nullable = false)
	private LocalDate publishingDate;

	@Column(nullable = false)
	private String funFact;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Rating rating;

	protected Review() {
	}

	public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText,
			final LocalDate publishingDate, final String funFact) {
		this.idReview = Objects.requireNonNull(idReview);
		this.version = Objects.requireNonNull(version);
		setApprovalStatus(approvalStatus);
		setReviewText(reviewText);
		setPublishingDate(publishingDate);
		setFunFact(funFact);
	}

	public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText,
			final String report, final LocalDate publishingDate,
			final String funFact, Product product, Rating rating, User user) {
		this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

		setReport(report);
		setProduct(product);
		setRating(rating);
		setUser(user);

	}

	public Review(final String reviewText, LocalDate publishingDate, Product product, String funFact, Rating rating,
			User user) {
		setReviewText(reviewText);
		setProduct(product);
		setPublishingDate(publishingDate);
		setApprovalStatus("pending");
		setFunFact(funFact);
		setRating(rating);
		setUser(user);
	}

	public Boolean setApprovalStatus(String approvalStatus) {

		if (approvalStatus.equalsIgnoreCase("pending") ||
				approvalStatus.equalsIgnoreCase("approved") ||
				approvalStatus.equalsIgnoreCase("rejected")) {

			this.approvalStatus = approvalStatus;
			return true;
		}
		return false;
	}

	public void setReviewText(String reviewText) {
		if (reviewText == null || reviewText.isBlank()) {
			throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
		}
		if (reviewText.length() > 2048) {
			throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
		}

		this.reviewText = reviewText;
	}

	public void setReport(String report) {
		if (report.length() > 2048) {
			throw new IllegalArgumentException("Report must not be greater than 2048 characters.");
		}
		this.report = report;
	}

	public Rating getRating() {
		if (rating == null) {
			return new Rating(0.0);
		}
		return rating;
	}

}
