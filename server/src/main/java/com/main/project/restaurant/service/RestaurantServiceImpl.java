package com.main.project.restaurant.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
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
    public RestaurantServiceImpl(NaverClient naverClient, RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.naverClient = naverClient;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }
    public RestaurantDto searchApi(String query){

        // 지역 검색 (var - 키워드는 지역 변수 타입 추론을 허용)
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.localSearch(searchLocalReq);
        var result = new RestaurantDto();

        if(searchLocalRes.getTotal() > 0) {

            var localItem = searchLocalRes.getItems().stream().findFirst().get();

                // 결과 리턴
                result.setRestaurantName(localItem.getTitle());
                result.setCategory(localItem.getCategory());
//                result.setDescription(localItem.getDescription());
//                result.setRestaurantPhone(localItem.getTelephone());
                result.setAddress(localItem.getAddress());
                result.setMapx(localItem.getMapx());
                result.setMapy(localItem.getMapy());

//            if(result.getAddress().equals(restaurantRepository.findByAddress(localItem.getAddress()))) {
//                Optional<Restaurant> restaurant = restaurantRepository.findByAddress(result.getAddress());
//                Restaurant restaurant1 = restaurant.get();
//                return entityToDto(restaurant1);
//            } //식당 중복 제거를 위한 비지니스 로직 구현

        }
        var restaurant = dtoToEntity(result);
        var saveRestaurant = restaurantRepository.save(restaurant); //검색 후 바로 저장, add 메서드 내용을 search에 추가
        return entityToDto(saveRestaurant);

    }
//public List<RestaurantDto> searchApi(String query){ //테스트 진행
//
//    // 지역 검색 (var - 키워드는 지역 변수 타입 추론을 허용)
//    var searchLocalReq = new SearchLocalReq();
//    searchLocalReq.setQuery(query);
//
//    var searchLocalRes = naverClient.localSearch(searchLocalReq);
//    var result = new RestaurantDto();
//
//    if(searchLocalRes.getTotal() > 0) {
//
//        var localItem = searchLocalRes.getItems().stream().findFirst().get();
//
//        // 결과 리턴
//        result.setRestaurantName(localItem.getTitle());
//        result.setCategory(localItem.getCategory());
////                result.setDescription(localItem.getDescription());
////                result.setRestaurantPhone(localItem.getTelephone());
//        result.setAddress(localItem.getAddress());
//        result.setMapx(localItem.getMapx());
//        result.setMapy(localItem.getMapy());
//
//    }
//    var restaurant = dtoToEntity(result);
//    var saveRestaurant = restaurantRepository.save(restaurant); //검색 후 바로 저장, add 메서드 내용을 search에 추가
//    return entityToDto(saveRestaurant);
//
//}

    private Restaurant dtoToEntity(RestaurantDto restaurantDto) {
        var restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantDto.getRestaurantId());
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setCategory(restaurantDto.getCategory());
//        restaurant.setRestaurantDescription(restaurantDto.getDescription());
//        restaurant.setRestaurantPhone(restaurantDto.getRestaurantPhone());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setMapx(restaurantDto.getMapx());
        restaurant.setMapy(restaurantDto.getMapy());

        return restaurant;
    }
    private RestaurantDto entityToDto(Restaurant restaurant) {
        var restaurantDto = new RestaurantDto();
        restaurantDto.setRestaurantId(restaurant.getRestaurantId());
        restaurantDto.setRestaurantName(restaurant.getRestaurantName());
        restaurantDto.setCategory(restaurant.getCategory());
        restaurantDto.setDescription(restaurant.getRestaurantDescription());
        restaurantDto.setRestaurantPhone(restaurant.getRestaurantPhone());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setMapx(restaurant.getMapx());
        restaurantDto.setMapy(restaurant.getMapy());

        return restaurantDto;
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