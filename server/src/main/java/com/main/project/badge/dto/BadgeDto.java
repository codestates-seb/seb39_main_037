package com.main.project.badge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BadgeDto {



    @AllArgsConstructor
    @Getter
    public static class postDto{

        String badgeName;
        String description;
    }

    @AllArgsConstructor
    @Getter
    public static class patchDto{
        long userId;
        String badgeName;
    }





    @NoArgsConstructor
    @Setter
    @Getter
    @AllArgsConstructor
    public static class responseDto{

        String badgeName;
        String description;
        String badgePhotoUrl;

    }




}
