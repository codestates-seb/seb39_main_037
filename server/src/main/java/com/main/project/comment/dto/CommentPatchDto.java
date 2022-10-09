package com.main.project.comment.dto;

import lombok.Getter;

@Getter
public class CommentPatchDto {

    private long commentId;
    private long userId;

    private String commentBody;
}
