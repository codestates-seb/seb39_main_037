package com.main.project.comment.mapper;

import com.main.project.comment.dto.CommentPatchDto;
import com.main.project.comment.dto.CommentPostDto;
import com.main.project.comment.dto.CommentResponseDto;
import com.main.project.comment.entity.Comment;
import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentPostDto commentPostDTO);
    Comment commentPatchDtoToComment(CommentPatchDto commentPatchDto);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);

    default public CommentResponseDto commentToCommentResponseDto(Comment comment){

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentId(comment.getCommentId());
        commentResponseDto.setCommentBody(comment.getCommentBody());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setUpdatedAt(comment.getUpdatedAt());
        commentResponseDto.setNickName(comment.getWebUser().getNickName());
        commentResponseDto.setUserId(comment.getWebUser().getUserId());
        commentResponseDto.setRestaurantId(comment.getReview().getRestaurant().getRestaurantId());
        commentResponseDto.setRestaurantName(comment.getReview().getRestaurant().getRestaurantName());
        commentResponseDto.setReviewNickName(comment.getReview().getWebUser().getNickName());
        commentResponseDto.setReviewTitle(comment.getReview().getReviewTitle());

        return commentResponseDto;
    }
}
