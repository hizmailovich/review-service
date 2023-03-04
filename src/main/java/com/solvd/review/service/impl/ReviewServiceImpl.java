package com.solvd.review.service.impl;

import com.solvd.review.domain.Review;
import com.solvd.review.domain.criteria.SearchCriteria;
import com.solvd.review.domain.exception.ResourceNotFoundException;
import com.solvd.review.persistence.ReviewRepository;
import com.solvd.review.service.ReviewService;
import com.solvd.review.service.property.MicroserviceProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MicroserviceProperty microserviceProperty;
    private final RestTemplate restTemplate;

    @Override
    public List<Review> retrieveByCriteria(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria) && Objects.nonNull(searchCriteria.getMovieId()) ?
                reviewRepository.findByMovieId(searchCriteria.getMovieId()) :
                reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        Boolean isExists = restTemplate.getForObject(
                microserviceProperty.getMovieUrl() + "/exists/" + review.getMovieId(),
                Boolean.class);
        if (Boolean.FALSE.equals(isExists)) {
            throw new ResourceNotFoundException("Movie with id = " + review.getMovieId() + " doesn't exist!");
        }
        review.setDate(LocalDate.now());
        return reviewRepository.save(review);
    }

}
