package com.solvd.review.service;

import com.solvd.review.domain.Review;

import java.util.List;

public interface ReviewService {

    List<Review> retrieveAll();

    Review create(Review review);

}
