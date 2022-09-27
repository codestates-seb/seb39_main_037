package com.main.project.badge.mapper;

import com.main.project.badge.dto.BadgeDto;
import com.main.project.badge.entity.Badge;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BadgeMapper {

    public Badge postDtoToBadge(BadgeDto.postDto postDto);


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
    List<BadgeDto.responseDto> badgesToResponseDtos(List<Badge> badge);
    
}
