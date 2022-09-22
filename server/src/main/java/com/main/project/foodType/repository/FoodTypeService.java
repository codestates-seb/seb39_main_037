package com.main.project.foodType.repository;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.foodType.FoodType;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class FoodTypeService {

    FoodTypeRepository foodTypeRepository;


    public FoodTypeService(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }


    public FoodType makeFoodType(FoodType foodType){
        if(!(foodTypeRepository.findByTypeName(foodType.getTypeName()).get() ==null)){//기존 타입과 중복되는지 체크
            new BusinessLogicException(ExceptionCode.FOODTYPE_DUPLICATE);
        }

        return foodTypeRepository.save(foodType);
    }


    public List<FoodType> findAllFoodType(){
       return foodTypeRepository.findAll();

    }

}
