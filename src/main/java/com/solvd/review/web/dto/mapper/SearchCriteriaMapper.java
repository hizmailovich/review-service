package com.solvd.review.web.dto.mapper;

import com.solvd.review.domain.criteria.SearchCriteria;
import com.solvd.review.web.dto.SearchCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchCriteriaMapper {

    SearchCriteria toEntity(SearchCriteriaDto searchCriteriaDto);

}
