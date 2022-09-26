package com.main.project.thumbUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.AssertTrue;

@Getter
public class ThumbUpDto {

    @Getter
    @AllArgsConstructor
    public static class PostDto {
        long reviewId;
        long userId;
    }
    @Getter
    @AllArgsConstructor
    public static class PatchDto {
        long reviewId;
        long userId;
    }
    @Getter
    @AllArgsConstructor
    public static class DeleteDto {
        long userId;
    }


}
