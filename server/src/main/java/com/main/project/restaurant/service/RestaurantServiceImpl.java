package com.main.project.restaurant.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.naver.NaverClient;
import com.main.project.naver.SearchLocalReq;
import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final NaverClient naverClient;
    private final RestaurantRepository restaurantRepository;

    public RestaurantDto search(String query){

        // 지역 검색 (var - 키워드는 지역 변수 타입 추론을 허용)
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.localSearch(searchLocalReq);

        if(searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

                // 결과 리턴
                var result = new RestaurantDto();
                result.setRestaurantName(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setDescription(localItem.getDescription());
                result.setRestaurantPhone(localItem.getTelephone());
                result.setAddress(localItem.getAddress());
                result.setReadAddress(localItem.getRoadAddress());

                return result;

        }
        return new RestaurantDto();
    }
    // db에 있는 MemoryRestaurant 에 데이터 등록
    public RestaurantDto add(RestaurantDto restaurantDto) {
        var restaurant = dtoToEntity(restaurantDto);
        var saveRestaurant = restaurantRepository.save(restaurant);
        return entityToDto(saveRestaurant);
    }

    private Restaurant dtoToEntity(RestaurantDto restaurantDto) {
        var restaurant = new Restaurant();
//        restaurant.setRestaurantId(restaurantDto.getRestaurantId());
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setCategory(restaurantDto.getCategory());
        restaurant.setRestaurantDescription(restaurantDto.getDescription());
        restaurant.setRestaurantPhone(restaurantDto.getRestaurantPhone());
        restaurant.setAddress(restaurantDto.getAddress());

        return restaurant;
    }
    private RestaurantDto entityToDto(Restaurant restaurant) {
        var restaurantDto = new RestaurantDto();
//        restaurantDto.setRestaurantId(restaurant.getRestaurantId());
        restaurantDto.setRestaurantName(restaurant.getRestaurantName());
        restaurantDto.setCategory(restaurant.getCategory());
        restaurantDto.setDescription(restaurant.getRestaurantDescription());
        restaurantDto.setRestaurantPhone(restaurant.getRestaurantPhone());
        restaurantDto.setAddress(restaurant.getAddress());

        return restaurantDto;
    }

    public List<RestaurantDto> findAll() {

        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> entityToDto(restaurant))
                .collect(Collectors.toList());
    }
    public Restaurant findRestaurant(long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        Restaurant foundRestaurant = restaurant.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));

        return foundRestaurant;
    }

    public void delete(long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}
