package com.isep.acme.dtos.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDTO {

    private String reviewText;

    private String userName;

    private Double rating;
}
