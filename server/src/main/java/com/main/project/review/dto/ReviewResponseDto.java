package com.main.project.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.user.entity.WebUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {
    private long reviewId;
    private String reviewTitle;
    private String reviewBody;
    private int view;
    private int thumbUp;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;
    private long restaurantId;

    private byte reviewImg;

    private long foodTypeId;
    private long foodId;
    private long locationId;

    private long userId;

    public void setUser(WebUser user) {
        this.userId = user.getUserId();
    }
}
