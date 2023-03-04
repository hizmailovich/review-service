package com.solvd.review.service.impl;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;
import com.solvd.review.domain.exception.ResourceNotFoundException;
import com.solvd.review.persistence.ReviewRepository;
import com.solvd.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final String MOVIES_URL = "http://movie/api/v1/movies";
    private final ReviewRepository reviewRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<Review> retrieveByCriteria(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getMovieId()) ?
                reviewRepository.findByMovieId(searchCriteria.getMovieId()) :
                reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        Boolean isExists = restTemplate.getForObject(MOVIES_URL + "/exists/" + review.getMovieId(),
                Boolean.class);
        if (Boolean.FALSE.equals(isExists)) {
            throw new ResourceNotFoundException("Movie with id = {} doesn't exist!" + review.getMovieId());
        }
        review.setDate(LocalDate.now());
        return reviewRepository.save(review);
    }

}
