package com.main.project.restaurant.controller;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.service.RestaurantService;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RestaurantDto restaurantDto) {

//        log.info("{}", wishListDto);
//
//        return wishListService.add(wishListDto);  -> 리턴 시 restaurantDto의 값으로 보내야함 -> mapper이용
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String query) {

//        restaurantService.search(query);  -> 리턴 시 restaurantDto의 값으로 보내야함 -> mapper이용
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
//        return restaurantService.findAll(); -> 리턴 시 List<RestaurantDto>의 값으로 보내야함 -> mapper이용

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{restaurant-id}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurant-id") long restaurantId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
