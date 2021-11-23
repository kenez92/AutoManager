package com.kenez92.automanager.car;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    CarDto getById(Long id) {
        if (id != null) {
            Optional<Car> car = carRepository.findById(id);
            if (car.isPresent()) {
                return carMapper.mapToCarDto(car.get());
            }
            throw new RuntimeException("There is no car with id + " + id);
        }
        throw new RuntimeException("Id cannot be empty");
    }
}
