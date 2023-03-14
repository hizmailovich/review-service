package com.solvd.review.service;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewClient {

    Flux<Review> retrieveByCriteria(SearchCriteria searchCriteria);

    Mono<Review> create(Review review);

    void delete(Long reviewId);

    void deleteByMovieId(Long movieId);

}
