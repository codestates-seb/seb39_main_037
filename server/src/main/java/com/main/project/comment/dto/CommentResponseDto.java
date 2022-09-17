package com.main.project.comment.dto;

import com.main.project.comment.entity.Comment;
import com.main.project.user.entity.WebUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    long commentId;
    String commentBody;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Setter
    private long userId;

    public void setUser(Comment user) {
        this.userId = user.getWebUser().getUserId();
    }

}
