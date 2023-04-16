package com.isep.acme.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isep.acme.model.Vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties 
public class ReviewDTO {
    private Long idReview;
    private String productSku;
    private String reviewText;
    private String userName;
    private String approvalStatus;
    private String funFact;
    private Double rating;
    private Set<VoteReviewDTO> votes;
    
}
