package com.solvd.review.web.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public record ExceptionDto(

        String field,
        String message

) {
}
