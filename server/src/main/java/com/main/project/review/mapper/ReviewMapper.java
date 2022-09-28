package com.main.project.review.mapper;

import com.main.project.location.entity.Location;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review reviewPostDtoToReview(ReviewPostDto reviewPostDto);

    Review reviewPatchDtoToReview(ReviewPatchDto reviewPatchDto);

    List<ReviewResponseDto> reviewsToReviewResponseDtos(List<Review> reviews);
//    public ReviewResponseDto reviewToReviewResponseDto(Review review);

    default public ReviewResponseDto reviewToReviewResponseDto(Review review) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setReviewId(review.getReviewId());
        reviewResponseDto.setNickname(review.getWebUser().getNickName());
        reviewResponseDto.setReviewTitle(review.getReviewTitle());
        reviewResponseDto.setReviewBody(review.getReviewBody());
        reviewResponseDto.setView(review.getView());
        reviewResponseDto.setThumbUp(review.getThumbUps().size());
        reviewResponseDto.setCreatedAt(review.getCreatedAt());
        reviewResponseDto.setUpdatedAt(review.getUpdatedAt());
        reviewResponseDto.setRestaurant(review.getRestaurant());
        reviewResponseDto.setRestaurantName(review.getRestaurant().getRestaurantName());
//        reviewResponseDto.setFoodType(review.getRestaurant().getFoodType());
        reviewResponseDto.setUserId(review.getWebUser().getUserId());

        return reviewResponseDto;
    }
}
