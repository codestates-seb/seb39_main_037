package com.main.project.food.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.repository.FoodRepository;
import com.main.project.foodType.entity.FoodType;
import com.main.project.foodType.repository.FoodTypeRepository;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.entity.RestaurantFood;
import com.main.project.restaurant.repository.RestaurantFoodRepository;
import com.main.project.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodServiceImple implements FoodService{

    FoodRepository foodRepository;
    FoodTypeRepository foodTypeRepository;
    RestaurantFoodRepository restaurantFoodRepository;
    RestaurantRepository restaurantRepository;

    public FoodServiceImple(FoodRepository foodRepository, FoodTypeRepository foodTypeRepository, RestaurantFoodRepository restaurantFoodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.restaurantFoodRepository = restaurantFoodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Food addFood(FoodDto.PostDto postDto) {

        if(foodRepository.findByFoodName(postDto.getFoodName()).isPresent()) {
            new BusinessLogicException(ExceptionCode.DUPLICATE_FOOD);
        }
        else{
            Food newFood = new Food();
            newFood.setFoodName(postDto.getFoodName());
            newFood.setFoodType(foodTypeRepository.findByTypeName(postDto.getFoodTypeName()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST)));

           return foodRepository.save(newFood);
        }
        return null;
    }

    @Override
    public Food editFoodInfo(String oldFoodName, String newFoodName) {
       Food toEditFood =  foodRepository.findByFoodName(oldFoodName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST));
        toEditFood.setFoodName(newFoodName);

        return foodRepository.save(toEditFood);

    }

    @Override
    public List<Food> random3Foods(String foodType) {

      FoodType foundfoodType =  foodTypeRepository.findByTypeName(foodType).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST));


        List<Food> threeRandomFoods =  foodRepository.findFilteredFoods(foundfoodType.getFoodTypeId());

        return threeRandomFoods;
    }


    public List<Food> random3FoodsByManyFilter(List<String> foodTypes){

        List<Food> filteredList = new ArrayList<>();
        List<Food> randomList = new ArrayList<>();

        for(String foodTypeName : foodTypes){
            filteredList.addAll(random3Foods(foodTypeName));
        }

        Random random = new Random();
        int randomIndex=0;
        Food foundFood = null;

        for(int i=0; i<3;i++) {
            randomIndex = random.nextInt(filteredList.size());//nextInt는 매개변수 bound만큼의(filteredList 사이즈 만큼) 랜덤한 숫자를 반환
            foundFood = filteredList.get(randomIndex);
            if(!randomList.contains(foundFood)) {
                randomList.add(filteredList.get(randomIndex));
            }
            else { i--;}
        }


        return randomList;

    }


    public List<Restaurant> findRestaurantByFood(long foodId, long locationId) {

       Food food = foodRepository.findById(foodId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOOD_NOT_EXIST));
         List<RestaurantFood> restaurantFood123 = restaurantFoodRepository.findAllByFood(food);


        List<RestaurantFood> restaurantFood = restaurantFood123.stream()
                 .filter(restaurantFood1 -> restaurantFood1.getLocation().getLocationId()==locationId)
                 .collect(Collectors.toList());;//음식 기준으로 모든 식당 필터링



        long[] restaurantList = new long[restaurantFood.size()];
        List<Restaurant> restaurants = new ArrayList<>();
        for(int i = 0; i<restaurantList.length; i++){
            restaurantList[i] = restaurantFood.get(i).getRestaurant().getRestaurantId();//조건에 맞는 레스토랑Id의 리스트
        }
        for(int i = 0; i<restaurantList.length; i++){
            restaurants.add(restaurantRepository.findById(restaurantList[i]).get());
        }

        return restaurants;
    }
}
