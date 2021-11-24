package com.kenez92.automanager.fuel;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
interface FuelMapper {

    FuelDto mapToFuelDto(Fuel fuel);
}
