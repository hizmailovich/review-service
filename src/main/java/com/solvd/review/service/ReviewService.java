package com.solvd.review.service;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;

import java.util.List;

public interface ReviewService {

    List<Review> retrieveByCriteria(SearchCriteria searchCriteria);

    Review create(Review review);

}
