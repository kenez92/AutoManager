package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.Car;
import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.car.CarService;
import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserNotFoundException;
import com.kenez92.automanager.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FuelService {
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
                fuel.setUserId(user.getId());
                fuelRepository.save(fuel);
            } else {
                throw new CarException("This car dont belongs to you!");
            }
        } else {
            throw new FuelException("Fuel cannot be null");
        }
    }

    public void deleteFuel(Principal principal, Long id) throws UserNotFoundException, FuelException {
        User user = userService.getUserByPrincipal(principal);
        Fuel fuel = fuelRepository.findById(id).orElseThrow(() -> new FuelException("Fuel not found!"));
        if (fuel.getUserId().equals(user.getId())) {
            fuelRepository.delete(fuel);
        } else {
            throw new FuelException("This information dont belongs to your account");
        }
    }
}
