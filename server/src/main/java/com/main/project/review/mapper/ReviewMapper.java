package com.main.project.review.mapper;

import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review reviewPostDtoToReview(ReviewPostDto reviewPostDto);
    Review reviewPatchDtoToReview(ReviewPatchDto reviewPatchDto);
    ReviewResponseDto reviewToReviewResponseDto(Review review);
    List<ReviewResponseDto> reviewsToReviewResponseDtos(List<Review> reviews);
}
