package com.solvd.review.web.dto.mapper;

import com.solvd.review.domain.Review;
import com.solvd.review.web.dto.ReviewDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(ReviewDto reviewDto);

    ReviewDto toDto(Review review);

    List<ReviewDto> toDto(List<Review> reviews);

}
