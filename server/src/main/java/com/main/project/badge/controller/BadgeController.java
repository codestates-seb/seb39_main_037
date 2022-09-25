package com.main.project.badge.controller;

import com.main.project.badge.dto.BadgeDto;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.mapper.BadgeMapper;
import com.main.project.badge.service.BadgeServiceImpl;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/badge")
public class BadgeController {

    BadgeServiceImpl  service;
    BadgeMapper mapper;

    public BadgeController(BadgeServiceImpl service, BadgeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postBadge(@RequestPart(required = false) MultipartFile badgeImgFile,
                                    BadgeDto.postDto postDto) throws IOException {
        Badge newbadge = mapper.postDtoToBadge(postDto);
        newbadge.setBadgeImg(badgeImgFile.getBytes());
        Badge postedBadge = service.makeNewBadge(newbadge);
        return new ResponseEntity(mapper.badgeToResponseDto(postedBadge), HttpStatus.CREATED);
    }

//    @PatchMapping("/assign")
//    public ResponseEntity assignBadge(@RequestBody long userId ){
//        use
//
//
//
//    }

    @GetMapping("/all")
    public ResponseEntity getAllBadge(){
        List<Badge> badgePage = service.findAllBadges();
        return new ResponseEntity(badgePage, HttpStatus.FOUND);
    }


    @DeleteMapping("/delete/{badgeId}")
    public ResponseEntity deleteBadge(@PathVariable("badgeId") long badgeId){

        service.removeBadge(badgeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




    private String uriMaker(Badge badge){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(String.valueOf(badge.getBadgeImg()))
                .toUriString();
    }


}
