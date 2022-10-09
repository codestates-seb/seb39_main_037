package com.main.project.thumbUp.mapper;

import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import com.main.project.thumbUp.dto.ThumbUpResponseDto;
import com.main.project.thumbUp.entity.ThumbUp;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ThumbUpMapper {

    List<ThumbUpResponseDto> thumbUpsToThumbUpResponseDtos (List<ThumbUp> thumbUps);

    default public ThumbUpResponseDto thumbUpToThumbUpResponseDto(ThumbUp thumbUp) {

        ThumbUpResponseDto thumbUpResponseDto = new ThumbUpResponseDto();
        thumbUpResponseDto.setUserId(thumbUp.getWebUser().getUserId());
        thumbUpResponseDto.setReviewId(thumbUp.getReview().getReviewId());
        thumbUpResponseDto.setRestaurantId(thumbUp.getReview().getRestaurant().getRestaurantId());
        thumbUpResponseDto.setReviewTitle(thumbUp.getReview().getReviewTitle());
        thumbUpResponseDto.setReviewBody(thumbUp.getReview().getReviewBody());
        thumbUpResponseDto.setRestaurantName(thumbUp.getReview().getRestaurant().getRestaurantName());
        thumbUpResponseDto.setReviewNickName(thumbUp.getReview().getWebUser().getNickName());

            return thumbUpResponseDto;
        }

}
