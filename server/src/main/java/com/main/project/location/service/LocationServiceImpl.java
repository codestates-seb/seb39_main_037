package com.main.project.location.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.location.dto.PostDto;
import com.main.project.location.entity.City;
import com.main.project.location.entity.Location;
import com.main.project.location.entity.State;
import com.main.project.location.repository.CityRepository;
import com.main.project.location.repository.LocationRepository;
import com.main.project.location.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{

    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;

    public LocationServiceImpl(StateRepository stateRepository, CityRepository cityRepository, LocationRepository locationRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.locationRepository = locationRepository;
    }


    public List<State> selectState() { //state를 리스트 형식으로 모두 불러옴
        return (List<State>) stateRepository.findAll();
    }
    public List<Location> selectCity(State state) { //해당 stateId를 가진 모든 location(state, city)을 불러옴
        return (List<Location>) locationRepository.findByState(state);
    }

    public State createState(State state) { // 관리자 권한 확인 필요
        state.setStateName(state.getStateName());
        return stateRepository.save(state);
    }

    public City createCity(City city) { // 관리자 권한 확인 필요
        city.setCityName(city.getCityName());
        return cityRepository.save(city);
    }
    public Location createLocation(PostDto.LocationPostDto locationPostDto){ // 관리자 권한 확인 필요
            Location newLocation = new Location();
        newLocation.setState(stateRepository.findById(locationPostDto.getStateId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.STATE_NOT_FOUND)));
        newLocation.setCity(cityRepository.findById(locationPostDto.getCityId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CITY_NOT_FOUND)));



//        location.setState(location.getState());
//        location.setCity(location.getCity());
//        verifyLocation(newLocation);
        return locationRepository.save(newLocation);
    }

    public Location findLocation(long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);

        Location foundLocation = location.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LOCATION_NOT_FOUND));

        return foundLocation;
    }

    public void deleteState(long stateId) {

        State state = findState(stateId);
        stateRepository.delete(state);

    }
    public void deleteCity(long cityId) {
        City city = findCity(cityId);
        cityRepository.delete(city);
    }
    public City findCity(long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        City foundCity = optionalCity.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ID_NOT_FOUND));
        return foundCity;
    }
    public State findState(long stateId) {
        Optional<State> optionalState = stateRepository.findById(stateId);
        State foundState = optionalState.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ID_NOT_FOUND));
        return foundState;
    }
    private void verifyLocation(Location location){
        // 해당 주가 존재하는지 확인
        findState(location.getState().getStateId());
        // 해당 도시가 존재하는지 확인
        findCity(location.getCity().getCityId());
    }
}
