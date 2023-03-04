package com.solvd.review.service.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "microservice")
@AllArgsConstructor
@Getter
public class MicroserviceProperty {

    private final String movieUrl;

}
