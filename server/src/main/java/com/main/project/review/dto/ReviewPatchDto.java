package com.main.project.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ReviewPatchDto {

    private long reviewId;
    private long userId;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String reviewTitle;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String reviewBody;

    @NotBlank(message = "별점을 선택해 주세요.")
    private int fiveStar;

    private byte reviewImg;


}
