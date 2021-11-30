package com.kenez92.automanager.car;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
interface CarMapper {

    CarDto mapToCarDto(Car car);

    Car mapToCar(CarDto carDto);
}
