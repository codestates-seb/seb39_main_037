package com.main.project.comment.mapper;

import com.main.project.comment.dto.CommentPatchDto;
import com.main.project.comment.dto.CommentPostDto;
import com.main.project.comment.dto.CommentResponseDto;
import com.main.project.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment postDtoToComment(CommentPostDto commentPostDTO);
    Comment patchDtoToComment(CommentPatchDto commentPatchDto);
    CommentResponseDto commentToResponseDto(Comment comment);
}
