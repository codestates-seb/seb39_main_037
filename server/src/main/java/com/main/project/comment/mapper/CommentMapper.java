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
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
