package com.main.project.thumbUp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThumbUpResponseDto {
    long userId;
    long reviewId;
    long restaurantId;
    String reviewTitle;
    String reviewBody;
    String restaurantName;
    String reviewNickName;
    String reviewPhotoUrl;

}
