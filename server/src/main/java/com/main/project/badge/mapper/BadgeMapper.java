package com.main.project.badge.mapper;

import com.main.project.badge.dto.BadgeDto;
import com.main.project.badge.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    Badge postDtoToBadge(BadgeDto.postDto postDto);
    BadgeDto.responseDto badgeToResponseDto(Badge badge);
}
