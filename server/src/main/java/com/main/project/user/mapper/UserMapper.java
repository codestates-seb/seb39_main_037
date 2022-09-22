package com.main.project.user.mapper;

import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    WebUser userPostDtoToWebUser(UserDto.postUserDto postUserDto);
    WebUser userPatchDtoToWebUser(UserDto.patchUserDto patchUserDto);
    WebUser userDeleteDtoToWebUser(UserDto.deleteUserDto deleteUserDto);
    UserDto.responseUserDto webUserToresponseUserDto(WebUser responseUser);
}
