package com.main.project.location.controller;

import com.main.project.location.dto.PostDto;
import com.main.project.location.entity.City;
import com.main.project.location.entity.Location;
import com.main.project.location.entity.State;
import com.main.project.location.mapper.LocationMapper;
import com.main.project.location.service.LocationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/location")
@Validated
@CrossOrigin("*")
public class LocationController {

    private final LocationServiceImpl locationService;
    private final LocationMapper locationMapper;

    public LocationController(LocationServiceImpl locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }


    @GetMapping("/state")
    public ResponseEntity getState () {

//      모든 state 보여줌(List all)
        List<State> stateList = locationService.selectState();

        return new ResponseEntity<>(locationMapper.statesToStateResponseDtos(stateList),
                HttpStatus.OK);
    }

    @GetMapping("/city/{state-id}")
    public ResponseEntity getCity (@PathVariable("state-id") long stateId) {
        State state = locationService.findState(stateId);

//      모든 시티 보여줌(List)(해당 stateId에 따른 list all)
        List<Location> cityList = locationService.selectCity(state);

        return new ResponseEntity<>(locationMapper.locationsToLocationResponseDtos(cityList),
                HttpStatus.OK);
    }

    @PostMapping("/manager/state")
    public ResponseEntity postState (@Valid @RequestBody PostDto.StatePostDto statePostDto) {

        // 관리자 검증하기
        State state = locationService.createState(locationMapper.statePostDtoToState(statePostDto));

        return new ResponseEntity<>(locationMapper.stateToStateResponseDto(state),
                HttpStatus.CREATED);
    }

    @PostMapping("/manager/city")
    public ResponseEntity postCity (@Valid @RequestBody PostDto.CityPostDto cityPostDto) {
        // 관리자 검증하기
        City city = locationService.createCity(locationMapper.cityPostDtoToCity(cityPostDto));

        return new ResponseEntity<>(locationMapper.cityToCityResponseDto(city),
                HttpStatus.CREATED);
    }

    @PostMapping("/manager/post")
    public ResponseEntity postLocation (@Valid @RequestBody PostDto.LocationPostDto locationPostDto) {
        // 관리자 검증하기
        long stateId = locationPostDto.getStateId();
        long cityId = locationPostDto.getCityId();

        Location location = locationService.createLocation(locationMapper.locationPostDtoToLocation(locationPostDto));

        return new ResponseEntity<>(locationMapper.locationToLocationResponseDto(location),
                HttpStatus.CREATED);
    }

    @GetMapping("/{location-id}")
    public ResponseEntity getLocation (@PathVariable("location-id") long locationId) {
        Location location = locationService.findLocation(locationId);
        return new ResponseEntity<>(locationMapper.locationToLocationResponseDto(location),
                HttpStatus.OK);
    }

    @DeleteMapping("/state/{state-id}/delete")
    public ResponseEntity deleteState (@PathVariable("state-id") long stateId) {
        // 관리자 검증하기
        locationService.deleteState(stateId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/city/{city-id}/delete")
    public ResponseEntity deleteCity (@PathVariable("city-id") long cityId) {
        // 관리자 검증하기
        locationService.deleteCity(cityId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
