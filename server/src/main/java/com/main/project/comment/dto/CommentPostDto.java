package com.main.project.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentPostDto {

    private long userId;
    private long reviewId;

    private String commentBody;
}
