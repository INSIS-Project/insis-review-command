package com.isep.acme.dtos;

import javax.persistence.Column;
import javax.persistence.Id;

import com.isep.acme.model.enumerate.voteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteReviewDTO {
	private Long voteId;
	private voteType voteType;
}