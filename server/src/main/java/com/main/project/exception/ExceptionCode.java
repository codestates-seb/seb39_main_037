package com.main.project.exception;

import lombok.Getter;

public enum ExceptionCode {
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다"),
    WRITER_IS_NOT_MATCH(404, "작성자가 아닙니다"),
    USER_IS_NOT_MATCH(   303, "입력한 사용자와 작성자가 다릅니다."),
    COMMENT_IS_NOT_EXIST(404, "해당 댓글을 찾을 수 없습니다"),
    LIKE_IS_NOT_EXISTS(404, "해당 좋아요를 찾을 수 없습니다");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
