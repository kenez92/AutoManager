package com.kenez92.automanager.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
interface UserMapper {

    UserDto mapToUserDto(User user);

    @Mapping(target = "password", ignore = true)
    UserDto mapToInnerUserDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User mapToUser(UserDto userDto);
}
