package com.main.project.review.dto;

import lombok.Getter;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class ReviewPatchDto {

    private long reviewId;
    private long userId;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String reviewTitle;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String reviewBody;

    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private Integer tasteStar;

    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private Integer facilityStar;


    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private Integer priceStar;

    private String reviewImgUrl;

}
