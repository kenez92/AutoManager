package com.kenez92.automanager.car;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
interface CarMapper {

    CarDto mapToCarDto(Car car);

    Car mapToCar(CarDto carDto);

    default List<CarDto> mapToCarDtoList(List<Car> cars) {
        List<CarDto> carDtos = new LinkedList<>();
        cars.forEach(car -> carDtos.add(mapToCarDto(car)));
        return carDtos;
    }
}
