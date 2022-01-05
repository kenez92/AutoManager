package com.kenez92.automanager.refuel;

import com.kenez92.automanager.car.Car;
import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.car.CarService;
import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserNotFoundException;
import com.kenez92.automanager.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class RefuelService {
    private final RefuelMapper refuelMapper;
    private final RefuelRepository refuelRepository;
    private final UserService userService;
    private final CarService carService;

    public RefuelService(RefuelMapper refuelMapper, RefuelRepository refuelRepository, UserService userService, CarService carService) {
        this.refuelMapper = refuelMapper;
        this.refuelRepository = refuelRepository;
        this.userService = userService;
        this.carService = carService;
    }

    public void addRefuel(RefuelDto refuelDto, Principal principal) throws UserNotFoundException, CarException, RefuelException {
        if (refuelDto != null && principal != null) {
            User user = userService.getUserByPrincipal(principal);
            Refuel refuel = refuelMapper.mapToRefuel(refuelDto);
            Car car = carService.getCarById(refuelDto.getCarId());
            if (car.getUser().getId().equals(user.getId())) {
                if (refuel.getKmTraveled() != null && refuel.getKmTraveled() != 0) {
                    refuel.calculateRealAvgConsumptionByKmTraveled();
                } else {
                    refuel.calculateRealAvgConsumptionByMileage(refuelRepository.findMaxMileage(refuelDto.getCarId()));
                }
                refuel.calculateCostPerLiter();
                refuel.setCar(car);
                refuel.setUserId(user.getId());
                refuelRepository.save(refuel);
            } else {
                throw new CarException("This car dont belongs to you!");
            }
        } else {
            throw new RefuelException("Fuel cannot be null");
        }
    }

    public void deleteRefuel(Principal principal, Long id) throws UserNotFoundException, RefuelException {
        User user = userService.getUserByPrincipal(principal);
        Refuel refuel = refuelRepository.findById(id).orElseThrow(() -> new RefuelException("Fuel not found!"));
        if (refuel.getUserId().equals(user.getId())) {
            refuelRepository.delete(refuel);
        } else {
            throw new RefuelException("This information dont belongs to your account");
        }
    }
}
