package com.main.project.badge.controller;

import com.main.project.badge.dto.BadgeDto;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.mapper.BadgeMapper;
import com.main.project.badge.repository.BadgeRepository;
import com.main.project.badge.service.BadgeService;
import com.main.project.badge.service.BadgeServiceImpl;
import com.main.project.entity.GlobalMethod;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.user.repository.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/badge")
public class BadgeController {

    BadgeServiceImpl badgeServiceImpl;
    BadgeMapper mapper;
    BadgeRepository badgeRepository;
    GlobalMethod globalMethod;

    public BadgeController(BadgeServiceImpl badgeServiceImpl, BadgeMapper mapper, BadgeRepository badgeRepository, GlobalMethod globalMethod) {
        this.badgeServiceImpl = badgeServiceImpl;
        this.mapper = mapper;
        this.badgeRepository = badgeRepository;
        this.globalMethod = globalMethod;
    }

    @PostMapping
    public ResponseEntity postBadge(@RequestPart MultipartFile badgeImgFile, @RequestPart
                                    BadgeDto.postDto postDto) throws IOException {
        Badge newbadge = mapper.postDtoToBadge(postDto);
        newbadge.setBadgeImg(badgeImgFile.getBytes());
        Badge postedBadge = badgeServiceImpl.makeNewBadge(newbadge);
        BadgeDto.responseDto test = mapper.badgeToResponseDto(postedBadge);

        String badgeImgUrl = globalMethod.uriMaker(postedBadge,"badge");
        test.setBadgePhotoUrl(badgeImgUrl);

        return new ResponseEntity(test, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity getAllBadge(){
        List<Badge> badgePage = badgeServiceImpl.findAllBadges();
        List<BadgeDto.responseDto> responseDtos = badgePage.stream()
                .map(badge -> new BadgeDto.responseDto(badge.getBadgeName(),badge.getDescription(),uriMaker(badge)))
                .collect(Collectors.toList());

        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/download/{badgename}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("badgename") String badgeName) throws Exception {
        Badge badge = badgeRepository.findByBadgeName(badgeName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.Badge_ID_IS_NOT_CORRECT));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "photo; filename=\"" + badge.getBadgeName()
                                + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(new ByteArrayResource(badge.getBadgeImg()));

    }

    @DeleteMapping("/delete/{badgeId}")
    public ResponseEntity deleteBadge(@PathVariable("badgeId") long badgeId){

        badgeServiceImpl.removeBadge(badgeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private String uriMaker(Badge badge){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/badge/download/")
                .path(String.valueOf(badge.getBadgeName()))
                .toUriString();
    }


}
