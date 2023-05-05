package com.solvd.review.domain;

import lombok.Data;

@Data
public class Event {

    private  Action action;
    private EsMovie movie;

    public enum Action {

        CREATE_MOVIE,
        DELETE_MOVIE,
        DELETE_REVIEW

    }

}
