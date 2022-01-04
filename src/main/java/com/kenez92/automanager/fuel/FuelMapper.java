package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.CarMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface FuelMapper {

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd HH:mm:ss")
    FuelDto mapToFuelDto(Fuel fuel);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Fuel mapToFuel(FuelDto fuelDto);
}
