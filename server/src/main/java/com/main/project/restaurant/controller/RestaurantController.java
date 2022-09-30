package com.main.project.restaurant.controller;

import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.dto.RestaurantPatchDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.mapper.RestaurantMapper;
import com.main.project.restaurant.service.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantServiceImpl;
    private final RestaurantMapper restaurantMapper;


    @GetMapping("/api/search")
    public ResponseEntity searchApi(@RequestParam String query) {


        Page<Restaurant> restaurant = restaurantServiceImpl.searchApi(query);
        List<Restaurant> restaurants = restaurant.getContent();

        return new ResponseEntity<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants), HttpStatus.OK);
    }
    @PatchMapping("/manager")
    public ResponseEntity patchRestaurant(@Valid @RequestBody RestaurantPatchDto restaurantPatchDto){
        Restaurant restaurant = restaurantServiceImpl.updateRestaurant(restaurantPatchDto.getRestaurantId(), restaurantPatchDto.getFoodTypeName());

        return new ResponseEntity<>(restaurantMapper.restaurantToRestaurantPatchResponseDto(restaurant), HttpStatus.OK);
    }

    @GetMapping("/search/{page}") // 사용자가 이용하는 검색 서비스 구현
    public ResponseEntity search(@RequestParam String title, @PathVariable("page") int page) {
        Page<Restaurant> pageRestaurant = restaurantServiceImpl.search(title, page - 1);
        List<Restaurant> restaurants = pageRestaurant.getContent();
        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants),pageRestaurant), HttpStatus.OK);
    }

    @GetMapping("{restaurant-id}") // 사용자가 이용하는 검색 서비스 구현
    public ResponseEntity findRestaurant(@PathVariable("restaurant-id") long restaurantId) {
        Restaurant restaurant = restaurantServiceImpl.findRestaurant(restaurantId);
        return new ResponseEntity<>(restaurantMapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity findAll(@PathVariable("page") int page) {

        Page<Restaurant> pageRestaurant = restaurantServiceImpl.findAll(page - 1, 10);
        List<Restaurant> restaurants = pageRestaurant.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants),pageRestaurant), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{restaurant-id}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurant-id") long restaurantId) {

        restaurantServiceImpl.delete(restaurantId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
