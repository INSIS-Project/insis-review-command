package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.isep.acme.model.enumerate.voteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    private long voteID;

    @Column
    private voteType voteType;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review; 

    @Column
    private String user;

    @Override
    public String toString() {
        return "Vote [voteID=" + voteID + ", voteType=" + voteType + ", user=" + user + "]";
    }
}
