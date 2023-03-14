package com.solvd.review.persistence;


import com.solvd.review.domain.Review;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewRepository extends R2dbcRepository<Review, Long> {

    Flux<Review> findByMovieId(Long movieId);

    Mono<Void> deleteAllByMovieId(Long movieId);

}
