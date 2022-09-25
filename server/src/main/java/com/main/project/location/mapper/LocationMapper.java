package com.main.project.location.mapper;

import com.main.project.location.dto.PostDto;
import com.main.project.location.dto.ResponseDto;
import com.main.project.location.entity.City;
import com.main.project.location.entity.Location;
import com.main.project.location.entity.State;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    State statePostDtoToState(PostDto.StatePostDto statePostDto);
    City cityPostDtoToCity(PostDto.CityPostDto cityPostDto);
    List<ResponseDto.StateResponseDto> statesToStateResponseDtos(List<State> states);
    List<ResponseDto.LocationResponseDto> locationsToLocationResponseDtos(List<Location> locations);
    ResponseDto.StateResponseDto stateToStateResponseDto(State state);
    ResponseDto.CityResponseDto cityToCityResponseDto(City city);

    // default 2개는 mapper에 State,City 적용이 안되서 따로 구현(mapperImpl파일에서 수정 불가능)

    default public Location locationPostDtoToLocation(PostDto.LocationPostDto locationPostDto) {
        if ( locationPostDto == null ) {return null;}

        Location location = new Location();
        State state = new State();
        City city = new City();

        state.setStateId(locationPostDto.getStateId());
        city.setCityId(locationPostDto.getCityId());

        location.setState(state);
        location.setCity(city);
        return location;
    }

    default public ResponseDto.LocationResponseDto locationToLocationResponseDto(Location location) {
        if ( location == null ) {
            return null;
        }

        ResponseDto.LocationResponseDto locationResponseDto = new ResponseDto.LocationResponseDto();

        locationResponseDto.setLocationId( location.getLocationId() );
        locationResponseDto.setStateName(location.getState().getStateName());
        locationResponseDto.setCityName(location.getCity().getCityName());

        return locationResponseDto;
    }
}
