package com.main.project.thumbUp.dto;

import lombok.Getter;

import javax.validation.constraints.AssertTrue;

@Getter
public class ThumbUpDto {

    private long reviewId;
    long userId;
    @AssertTrue
    boolean like;
}
