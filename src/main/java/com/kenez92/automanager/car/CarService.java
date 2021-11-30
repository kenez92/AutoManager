package com.kenez92.automanager.car;

import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final UserRepository userRepository;

    public CarService(CarRepository carRepository, CarMapper carMapper, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.userRepository = userRepository;
    }

    CarDto getById(Long id, Principal principal) {
        if (id != null) {
            Car car = carRepository.findById(id).orElseThrow(()
                    -> new RuntimeException("Car not found"));
            User user = userRepository.findUserByUserName(principal.getName()).orElseThrow(()
                    -> new RuntimeException("User not found"));
            if (car.getUser().getId().equals(user.getId())) {
                return carMapper.mapToCarDto(car);
            } else {
                throw new RuntimeException("This car dont belongs to you!");
            }
        }
        throw new RuntimeException("Id cannot be empty");
    }

    public void createCar(CarDto carDto, Principal principal) {
        if (carDto != null) {
            Optional<User> user = userRepository.findUserByUserName(principal.getName());
            if (user.isPresent()) {
                Car car = carMapper.mapToCar(carDto);
                car.setUser(user.get());
                carRepository.save(car);
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Car cannot be empty");
        }
    }
}
