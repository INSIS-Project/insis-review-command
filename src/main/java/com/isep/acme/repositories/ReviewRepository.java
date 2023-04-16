package com.isep.acme.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
