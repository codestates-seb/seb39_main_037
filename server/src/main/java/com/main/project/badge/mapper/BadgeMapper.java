package com.main.project.badge.mapper;

import com.main.project.badge.dto.BadgeDto;
import com.main.project.badge.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    default public Badge postDtoToBadge(BadgeDto.postDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Badge badge = new Badge();
        badge.setBadgename(postDto.getBadgeName());
        badge.setBadgeDescription(postDto.getDescription());

        return badge;
    }


    default public BadgeDto.responseDto badgeToResponseDto(Badge badge) {
        if ( badge == null ) {
            return null;
        }

        BadgeDto.responseDto responseDto = new BadgeDto.responseDto();
        responseDto.setBadgeName(badge.getBadgename());
        responseDto.setDescription(badge.getBadgeDescription());
        responseDto.setBadgePhotoUrl(badge.getBadgeImg().toString());

        return responseDto;
    }
    
}
