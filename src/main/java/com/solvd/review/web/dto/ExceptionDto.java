package com.solvd.review.web.dto;

import lombok.Builder;

@Builder
public record ExceptionDto(

        String field,
        String message

) {
}
