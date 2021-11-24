package com.kenez92.automanager.fuel;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class FuelService {
    private final FuelMapper fuelMapper;
    private final FuelRepository fuelRepository;

    public FuelService(FuelMapper fuelMapper, FuelRepository fuelRepository) {
        this.fuelMapper = fuelMapper;
        this.fuelRepository = fuelRepository;
    }

    FuelDto getById(Long id) {
        if (id != null) {
            Optional<Fuel> fuel = fuelRepository.findById(id);
            if (fuel.isPresent()) {
                return fuelMapper.mapToFuelDto(fuel.get());
            }
            throw new RuntimeException("Fuel not found");
        }
        throw new RuntimeException("Fuel id cannot be empty");
    }
}
