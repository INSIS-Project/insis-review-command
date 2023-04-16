package com.isep.acme.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.dtos.VoteReviewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @Column(nullable = false)
    private String approvalStatus;

    @Column(nullable = false)
    private String reviewText;
    
    @Column(nullable = false)
    private String funFact;

    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Double rating;
    
    @OneToMany(mappedBy="review", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Vote> votes = new HashSet<Vote>();

    public Review(final Long idReview,
            final String approvalStatus,
            final String reviewText,
            final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setFunFact(funFact);
    }

    public Review(final Long idReview,
            final String approvalStatus,
            String reviewText,
            final String funFact,
            Product product,
            Double rating,
            String userName) {
        this(idReview, approvalStatus, reviewText, funFact);
        setProduct(product);
        setRating(rating);
        setUserName(userName);

    }

    public Review(final String reviewText,
            Product product,
            String funFact,
            Double rating,
            String userName) {
        setReviewText(reviewText);
        setProduct(product);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUserName(userName);
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

    public Set<Vote> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setReview(this);
    }

  
}