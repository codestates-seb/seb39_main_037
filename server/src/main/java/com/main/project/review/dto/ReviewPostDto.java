package com.main.project.review.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class ReviewPostDto {

    @NotBlank(message = "제목을 입력해 주세요.")
    private String reviewTitle;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String reviewBody;

    @NotBlank(message = "맛 별점을 입력해 주세요.")
    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private int tasteStar;

    @NotBlank(message = "시설 별점을 입력해 주세요.")
    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private int facilityStar;

    @NotBlank(message = "가격 별점을 입력해 주세요.")
    @Min(value = 1, message = "별점은 최소 1점 부터 시작합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지 줄 수 있습니다.")
    private int priceStar;

    private long userId;
    private long restaurantId;
    private long foodId;
    private long locationId;

}
