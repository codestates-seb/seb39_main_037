package com.main.project.user.dto;

import com.main.project.badge.UserBadge;
import com.main.project.comment.entity.Comment;
import com.main.project.review.entity.Review;
import com.main.project.thumbUp.entity.ThumbUp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class postUserDto{
        String userName;//본명
        String nickName;//닉네임
        @Email
        String email;//이메일
        String password;
    }


    @Getter
    @NoArgsConstructor
    public static class patchUserDto{
        String userName;
        String nickName;
        @Email
        String email;
    }

    @Getter
    @NoArgsConstructor
    public static class patchUserpasswordDto{
        long userId;
        String oldPassWord;
        String newPassword;

    }

    @Getter
    @NoArgsConstructor
    public static class getMyUserActivityDetailsDto{
        long userId;
    }






    @Getter
    @NoArgsConstructor
    public static class deleteUserDto{
        long userId;
        String password;
// 유저 계정을 삭제할 경우 계정 해지 사유를 확인하는 설문 조사도 가능할 듯
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class responseUserDto{
        long userId;
        private String userName;
        private String nickName;
        private String email;
        private String imgUrl;

// 유저 계정을 삭제할 경우 계정 해지 사유를 확인하는 설문 조사도 가능할 듯
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseUserActivityDto{
        List<Comment> ListOfComment;
        List<Review> ListOfReview;
        List<ThumbUp> ListOfThumbUp;
        List<UserBadge> ListOfBadge;

    }








    @AllArgsConstructor
    @Getter
    public static class responseWithPhotoUrlDTO{
        long userId;
        private String userName;
        private String nickName;
        private String email;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        String photoUrl;

    }



}
