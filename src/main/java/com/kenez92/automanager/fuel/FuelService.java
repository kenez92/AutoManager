package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.Car;
import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.car.CarService;
import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserNotFoundException;
import com.kenez92.automanager.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
class FuelService {
    private final FuelMapper fuelMapper;
    private final FuelRepository fuelRepository;
    private final UserService userService;
    private final CarService carService;

    public FuelService(FuelMapper fuelMapper, FuelRepository fuelRepository, UserService userService, CarService carService) {
        this.fuelMapper = fuelMapper;
        this.fuelRepository = fuelRepository;
        this.userService = userService;
        this.carService = carService;
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

    public void addFuel(FuelDto fuelDto, Principal principal) throws UserNotFoundException, CarException, FuelException {
        if (fuelDto != null && principal != null) {
            User user = userService.getUserByPrincipal(principal);
            Fuel fuel = fuelMapper.mapToFuel(fuelDto);
            Car car = carService.getCarById(fuelDto.getCarId());
            if (car.getUser().getId().equals(user.getId())) {
                if (fuel.getKmTraveled() != null && fuel.getKmTraveled() != 0) {
                    fuel.calculateRealAvgConsumptionByKmTraveled();
                } else {
                    fuel.calculateRealAvgConsumptionByMileage(fuelRepository.findMaxMileage(fuelDto.getCarId()));
                }
                fuel.calculateCostPerLiter();
                fuel.setCar(car);
                fuelRepository.save(fuel);
            } else {
                throw new CarException("This car dont belongs to you!");
            }
        } else {
            throw new FuelException("Fuel cannot be null");
        }
    }
}
