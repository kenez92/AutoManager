package com.kenez92.automanager.car;

import com.kenez92.automanager.fuel.FuelService;
import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserNotFoundException;
import com.kenez92.automanager.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final UserService userService;

    public CarService(CarRepository carRepository, CarMapper carMapper, UserService userService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.userService = userService;
    }

    CarDto getById(Long id, Principal principal) throws UserNotFoundException, CarException {
        if (id != null) {
            Car car = carRepository.getWithFuelById(id).orElseThrow(()
                    -> new RuntimeException("Car not found"));
            User user = userService.getUserByPrincipal(principal);
            if (car.getUser().getId().equals(user.getId())) {
                return carMapper.mapToCarDto(car);
            } else {
                throw new CarException("This car dont belongs to you!");
            }
        }
        throw new RuntimeException("Id cannot be empty");
    }

    public void createCar(CarDto carDto, Principal principal) throws UserNotFoundException, CarException {
        if (carDto != null) {
            User user = userService.getUserByPrincipal(principal);
            Car car = carMapper.mapToCar(carDto);
            car.setUser(user);
            carRepository.save(car);
        } else {
            throw new CarException("Car cannot be empty");
        }
    }

    public List<CarDto> getCarsByPrincipal(Principal principal) throws UserNotFoundException {
        User user = userService.getUserByPrincipal(principal);
        List<Car> cars = carRepository.findByUser(user);
        if (cars.isEmpty()) return new ArrayList<>();
        return carMapper.mapToCarDtoList(cars);
    }

    public Car getCarById(Long carId) throws CarException {
        if (carId != null) {
            return carRepository.findById(carId).orElseThrow(() -> new CarException("Car not found!"));
        }
        throw new CarException("Car id cannot be null");
    }
}
