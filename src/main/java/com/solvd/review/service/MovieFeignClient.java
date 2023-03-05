package com.solvd.review.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie", url = "${microservice.movie-url}")
public interface MovieFeignClient {

    @GetMapping("/exists/{movieId}")
    Boolean isExists(@PathVariable Long movieId);

}
