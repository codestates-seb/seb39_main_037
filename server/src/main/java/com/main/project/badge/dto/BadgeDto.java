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

    @NoArgsConstructor
    @Setter
    @Getter
    public static class responseDto{

        String badgeName;
        String description;
        String badgePhotoUrl;

    }




}
