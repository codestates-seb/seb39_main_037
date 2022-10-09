package com.main.project.qna.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class QnaDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDto{
        long userId;
        @NotBlank(message = "제목을 입력해 주세요.")
        String questionTitle;
        @NotBlank(message = "내용을 입력해 주세요.")
        String questionBody;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class PatchDto {
        long userId;
        long qnaId;
        @NotBlank(message = "제목을 입력해 주세요.")
        String questionTitle;
        @NotBlank(message = "내용을 입력해 주세요.")
        String questionBody;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ResponseDto {
        long userId;
        long qnaId;
        @JsonFormat(pattern="yyyy-MM-dd")
        private LocalDateTime createdAt;
        @JsonFormat(pattern="yyyy-MM-dd")
        private LocalDateTime updatedAt;
        String questionTitle;
        String questionBody;
    }
}
