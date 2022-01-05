package com.kenez92.automanager.refuel;

import com.kenez92.automanager.car.CarMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface RefuelMapper {

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd HH:mm:ss")
    RefuelDto mapToRefuelDto(Refuel refuel);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Refuel mapToRefuel(RefuelDto refuelDto);
}
