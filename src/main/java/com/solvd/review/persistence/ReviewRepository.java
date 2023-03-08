package com.solvd.review.persistence;


import com.solvd.review.domain.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Long> {

    Flux<Review> findByMovieId(Long movieId);

}
