package com.main.project.entity;

import com.main.project.badge.entity.Badge;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class GlobalMethod {


    public String uriMaker(Badge badge, String path){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/"+ path + "/download/")
                .path(String.valueOf(badge.getBadgeName()))
                .toUriString();
    }

}
