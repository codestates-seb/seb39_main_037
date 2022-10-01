package com.main.project.restaurant.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.foodType.service.FoodTypeServiceImpl;
import com.main.project.location.entity.City;
import com.main.project.location.entity.Location;
import com.main.project.location.entity.State;
import com.main.project.location.service.LocationServiceImpl;
import com.main.project.naver.NaverClient;
import com.main.project.naver.SearchLocalReq;
import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.repository.RestaurantRepository;
import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.review.service.ReviewServiceImpl;
import com.main.project.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    private final NaverClient naverClient;
    private final RestaurantRepository restaurantRepository;
    ReviewRepository reviewRepository;
    LocationServiceImpl locationService;
    FoodTypeServiceImpl foodTypeService;
    public RestaurantServiceImpl(NaverClient naverClient, RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, LocationServiceImpl locationService, FoodTypeServiceImpl foodTypeService) {
        this.naverClient = naverClient;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.locationService = locationService;
        this.foodTypeService = foodTypeService;
    }
    public Page<Restaurant> searchApi(String query){

        // 지역 검색 (var - 키워드는 지역 변수 타입 추론을 허용)
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.localSearch(searchLocalReq);
        var result = new RestaurantDto();

        if(searchLocalRes.getTotal() > 0) {

            var localItem = searchLocalRes.getItems();
            for(var test: localItem){
                result.setRestaurantName(test.getTitle());
                result.setCategory(test.getCategory());
                result.setAddress(test.getAddress());
                result.setMapx(test.getMapx());
                result.setMapy(test.getMapy());
                var restaurant = dtoToEntity(result);
                restaurantRepository.save(restaurant);
            }


        }
        return findAll(0,5);

    }

    private Restaurant dtoToEntity(RestaurantDto restaurantDto) {
        var restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantDto.getRestaurantId());
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setCategory(restaurantDto.getCategory());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setMapx(restaurantDto.getMapx());
        restaurant.setMapy(restaurantDto.getMapy());

        return restaurant;
    }
    public Restaurant updateRestaurant(long restaurantId, String foodTypeName) { // 타입 등록시 지역 자동 추가
        Restaurant findRestaurant = findRestaurant(restaurantId);
//        Optional.ofNullable(restaurant.getFoodType())
//                .ifPresent(findRestaurant::setFoodType);
        findRestaurant.addFoodType(foodTypeService.findFoodType(foodTypeName));
        if(!findRestaurant.getAddress().isEmpty()){
            String[] arr = findRestaurant.getAddress().split(" ");
            String addState = arr[0];
            String addCity = arr[1];
            State state = locationService.foundState(addState);
            City city = locationService.foundCity(addCity);
            Location location = locationService.findByLocation(state, city);
            findRestaurant.setLocation(location);
        }


        return restaurantRepository.save(findRestaurant);
    }

    public Page<Restaurant> findAll(int page, int size) {

        return restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("restaurantId").descending()));
    }
    public Restaurant findRestaurant(long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        Restaurant foundRestaurant = restaurant.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));

        return foundRestaurant;
    }

    @Transactional
    public Page<Restaurant> search(String title, int page) { //리뷰 검색기능 구현

        return restaurantRepository.findByRestaurantNameContaining(title, PageRequest.of(page, 10, Sort.by("restaurantId").descending()));

    }

    public void delete(long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    public Restaurant aveStar(Review review) { //리뷰 별점 평균을 식당 데이터에 저장하는 로직
        Restaurant restaurant = findRestaurant(review.getRestaurant().getRestaurantId());
        double avgTasteStar = restaurant.getReviewList().stream()
                .map(review1 -> review.getTasteStar())
                .mapToInt(tasteStar -> tasteStar)
                .average()
                .getAsDouble();
        restaurant.setAveTaste(avgTasteStar);

        double avgFacilityStar = restaurant.getReviewList().stream()
                .map(review1 -> review.getFacilityStar())
                .mapToInt(facilityStar -> facilityStar)
                .average()
                .getAsDouble();
        restaurant.setAveFacility(avgFacilityStar);
        double avgPriceStar = restaurant.getReviewList().stream()
                .map(review1 -> review.getPriceStar())
                .mapToInt(priceStar -> priceStar)
                .average()
                .getAsDouble();
        restaurant.setAveTaste(avgTasteStar);

        return restaurantRepository.save(restaurant);
    }

}