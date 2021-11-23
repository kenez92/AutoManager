package com.kenez92.automanager.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
interface CarMapper {

//    @Mapping(ignore = true, target = "id")
    CarDto mapToCarDto(Car car);
}
