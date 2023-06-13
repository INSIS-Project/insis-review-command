package com.isep.acme.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.model.Vote;


@Component
public class VoteMapper {

    public VoteDTO toVoteDTO(String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            VoteDTO voteDTO = mapper.readValue(body, VoteDTO.class);
            return voteDTO;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vote toEntity(VoteDTO voteReviewDTO) {
        Vote vote = new Vote();
        vote.setVoteID(voteReviewDTO.getVoteId());
        vote.setVoteType(voteReviewDTO.getVoteType());
        vote.setUser(voteReviewDTO.getUser());
        return vote;
    }
}
