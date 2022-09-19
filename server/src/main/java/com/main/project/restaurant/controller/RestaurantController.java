package com.main.project.restaurant.controller;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.mapper.RestaurantMapper;
import com.main.project.restaurant.service.RestaurantServiceImpl;
import com.main.project.review.mapper.ReviewMapper;
import com.main.project.review.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantServiceImpl restaurantServiceImpl;
    private final RestaurantMapper restaurantMapper;



    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RestaurantDto restaurantDto) {

        log.info("{}", restaurantDto);

        RestaurantDto restaurant = restaurantServiceImpl.add(restaurantDto);
        return new ResponseEntity<>(restaurantMapper.restaurantDtoToRestaurant(restaurant), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String query) {

        RestaurantDto restaurant = restaurantServiceImpl.search(query);
        return new ResponseEntity<>(restaurantMapper.restaurantDtoToRestaurant(restaurant), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {

        List<RestaurantDto> restaurantList = restaurantServiceImpl.findAll();

        return new ResponseEntity<>(restaurantMapper.restaurantsToRestaurantDtos(restaurantList), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{restaurant-id}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurant-id") long restaurantId) {

        restaurantServiceImpl.delete(restaurantId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
