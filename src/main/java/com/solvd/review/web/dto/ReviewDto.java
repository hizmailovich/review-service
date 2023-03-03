package com.solvd.review.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReviewDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,

        @NotBlank(message = "Name can't be blank!")
        String name,

        @NotBlank(message = "Text of review can't be blank!")
        String description,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDate date,

        @NotNull(message = "Movie can't be empty!")
        Long movieId

) {
}
