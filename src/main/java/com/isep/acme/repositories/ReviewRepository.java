package com.isep.acme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.isep.acme.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
