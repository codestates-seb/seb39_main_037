package com.main.project.thumbUp.dto;

import lombok.Getter;

import javax.validation.constraints.AssertTrue;

@Getter
public class ThumbUpDto {

    long thumbUpId;
    long reviewId;
    long userId;
}
