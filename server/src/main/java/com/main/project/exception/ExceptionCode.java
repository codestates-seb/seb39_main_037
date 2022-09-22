package com.main.project.exception;

import lombok.Getter;

public enum ExceptionCode {
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다"),
    WRITER_IS_NOT_MATCH(404, "작성자가 아닙니다"),
    USER_IS_NOT_MATCH(   303, "입력한 사용자와 작성자가 다릅니다."),
    USER_IS_NOT_EXIST(   304, "해당 이메일의 유저가 존재하지 않습니다."),
    COMMENT_IS_NOT_EXIST(404, "해당 댓글을 찾을 수 없습니다"),
    RESTAURANT_NOT_FOUND(404, "해당 식당을 찾을 수 없습니다."),
    LIKE_IS_NOT_EXISTS(404, "해당 좋아요를 찾을 수 없습니다"),

    PASSWORD_NOT_MATCH(601, "비밀번호가 맞지 않습니다 "),
    FOODTYPE_DUPLICATE(701, "동일한 푸드타입이 존재합니다. "),
    Badge_ID_IS_NOT_CORRECT(701, "일치하는 뱃지가 없습니다");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
