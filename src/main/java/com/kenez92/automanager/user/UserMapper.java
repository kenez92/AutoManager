package com.kenez92.automanager.user;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
interface UserMapper {

    UserDto mapToUserDto(User user);
}
