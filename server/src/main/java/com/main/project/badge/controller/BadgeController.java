package com.main.project.badge.controller;

import com.main.project.badge.dto.BadgeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/badge")
public class BadgeController {


    @PostMapping
    public String postBadge(@RequestPart(required = false) MultipartFile badgeImgFile,
                            BadgeDto.postDto postDto){

        return "new Badge";
    }



}
