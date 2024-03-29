package com.solvd.review.web.controller;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;
import com.solvd.review.service.ReviewService;
import com.solvd.review.web.dto.ReviewDto;
import com.solvd.review.web.dto.SearchCriteriaDto;
import com.solvd.review.web.dto.mapper.ReviewMapper;
import com.solvd.review.web.dto.mapper.SearchCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final SearchCriteriaMapper searchCriteriaMapper;

    @GetMapping()
    public Flux<ReviewDto> getAll(SearchCriteriaDto searchCriteriaDto) {
        SearchCriteria searchCriteria = this.searchCriteriaMapper.toEntity(searchCriteriaDto);
        Flux<Review> reviews = this.reviewService.retrieveByCriteria(searchCriteria);
        return reviews.map(this.reviewMapper::toDto);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReviewDto> create(@Validated @RequestBody ReviewDto reviewDto) {
        Review review = this.reviewMapper.toEntity(reviewDto);
        Mono<Review> reviewMono = this.reviewService.create(review);
        return reviewMono.map(this.reviewMapper::toDto);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long reviewId) {
        this.reviewService.delete(reviewId);
    }

}
