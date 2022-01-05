package com.kenez92.automanager.car;

import com.kenez92.automanager.refuel.RefuelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {RefuelMapper.class})
public interface CarMapper {

    @Mapping(target = "refuelDtoList", source = "refuelList")
    CarDto mapToCarDto(Car car);

    @Mapping(ignore = true, target = "refuelList")
    Car mapToCar(CarDto carDto);

    default List<CarDto> mapToCarDtoList(List<Car> cars) {
        List<CarDto> carDtos = new LinkedList<>();
        cars.forEach(car -> carDtos.add(mapToCarDto(car)));
        return carDtos;
    }
}
