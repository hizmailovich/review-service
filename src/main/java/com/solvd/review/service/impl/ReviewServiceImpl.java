package com.solvd.review.service.impl;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;
import com.solvd.review.domain.exception.ResourceNotFoundException;
import com.solvd.review.persistence.ReviewRepository;
import com.solvd.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Value("${services.movie-url}")
    private String movieUrl;

    @Value("${services.rating-url}")
    private String ratingUrl;

    private final ReviewRepository reviewRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public Flux<Review> retrieveByCriteria(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria) && Objects.nonNull(searchCriteria.getMovieId()) ?
                this.reviewRepository.findByMovieId(searchCriteria.getMovieId()) :
                this.reviewRepository.findAll();
    }

    @Override
    public Mono<Review> create(Review review) {
        Mono<Boolean> exists = this.webClientBuilder.build()
                .get()
                .uri(this.movieUrl + "/exists/{movieId}", review.getMovieId())
                .retrieve()
                .bodyToMono(Boolean.class);
        return exists.flatMap(value -> {
            if (Boolean.FALSE.equals(value)) {
                return Mono.error(new ResourceNotFoundException("Movie with id = " + review.getMovieId() + " doesn't exist!"));
            } else {
                review.setDate(LocalDate.now());
                return this.webClientBuilder.build()
                        .put()
                        .uri(this.ratingUrl + "?movieId=" + review.getMovieId() + "&mark=" + review.getMark())
                        .retrieve()
                        .toBodilessEntity()
                        .then(this.reviewRepository.save(review));
            }
        });
    }

    @Override
    public void delete(Long reviewId) {
        this.reviewRepository.deleteById(reviewId).subscribe();
    }

    @Override
    public void deleteByMovieId(Long movieId) {
        this.reviewRepository.deleteAllByMovieId(movieId).subscribe();
    }

}
