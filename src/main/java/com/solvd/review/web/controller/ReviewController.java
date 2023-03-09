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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final SearchCriteriaMapper searchCriteriaMapper;

    @GetMapping()
    public List<ReviewDto> getAll(@RequestParam(required = false) SearchCriteriaDto searchCriteriaDto) {
        SearchCriteria searchCriteria = searchCriteriaMapper.toEntity(searchCriteriaDto);
        List<Review> reviews = reviewService.retrieveByCriteria(searchCriteria);
        return reviewMapper.toDto(reviews);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto create(@Validated @RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review = reviewService.create(review);
        return reviewMapper.toDto(review);
    }

}
