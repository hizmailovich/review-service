package com.solvd.review.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReviewDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,

        @NotBlank(message = "Name can't be blank!")
        String name,

        @NotBlank(message = "Text of review can't be blank!")
        String text,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDate date,

        @Min(value = 0)
        @Max(value = 10)
        @NotNull(message = "Mark can't be empty!")
        Integer mark,

        @NotNull(message = "Movie can't be empty!")
        Long movieId

) {
}
