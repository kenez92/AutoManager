package com.kenez92.automanager.fuel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
interface FuelMapper {

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    FuelDto mapToFuelDto(Fuel fuel);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    Fuel mapToFuel(FuelDto fuelDto);
}
