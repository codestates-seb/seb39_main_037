package com.main.project.restaurant.controller;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.mapper.RestaurantMapper;
import com.main.project.restaurant.service.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/restaurant")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantServiceImpl;
    private final RestaurantMapper restaurantMapper;


    @GetMapping("/api/search")
    public ResponseEntity searchApi(@RequestParam String query) {


        RestaurantDto restaurant = restaurantServiceImpl.searchApi(query);

        return new ResponseEntity<>(restaurantMapper.restaurantDtoToRestaurant(restaurant), HttpStatus.OK); // 코드 재구현
    }

    @GetMapping("/search") // 사용자가 이용하는 검색 서비스 구현
    public ResponseEntity search(@RequestBody String title) {
        List<Restaurant> restaurants = restaurantServiceImpl.search(title);

        return new ResponseEntity<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity findAll(@PathVariable("page") int page) {

        int size =10;
        Page<Restaurant> pageRestaurant = restaurantServiceImpl.findAll(page - 1, size);
        List<Restaurant> restaurants = pageRestaurant.getContent();

        return new ResponseEntity<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{restaurant-id}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurant-id") long restaurantId) {

        restaurantServiceImpl.delete(restaurantId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
