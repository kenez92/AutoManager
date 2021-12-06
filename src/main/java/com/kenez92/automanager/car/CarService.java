package com.kenez92.automanager.car;

import com.kenez92.automanager.user.User;
import com.kenez92.automanager.user.UserNotFoundException;
import com.kenez92.automanager.user.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

    CarDto getById(Long id, Principal principal) throws UserNotFoundException {
        if (id != null) {
            Car car = carRepository.findById(id).orElseThrow(()
                    -> new RuntimeException("Car not found"));
            User user = getUserByPrincipal(principal);
            if (car.getUser().getId().equals(user.getId())) {
                return carMapper.mapToCarDto(car);
            } else {
                throw new RuntimeException("This car dont belongs to you!");
            }
        }
        throw new RuntimeException("Id cannot be empty");
    }

    public void createCar(CarDto carDto, Principal principal) throws UserNotFoundException {
        if (carDto != null) {
            User user = getUserByPrincipal(principal);
            Car car = carMapper.mapToCar(carDto);
            car.setUser(user);
            carRepository.save(car);
        } else {
            throw new RuntimeException("Car cannot be empty");
        }
    }

    public List<CarDto> getCarsByPrincipal(Principal principal) throws UserNotFoundException {
        User user = getUserByPrincipal(principal);
        List<Car> cars = carRepository.findByUser(user);
        if (cars.isEmpty()) return new ArrayList<>();
        return carMapper.mapToCarDtoList(cars);
    }

    private User getUserByPrincipal(Principal principal) throws UserNotFoundException {
        if (principal == null) {
            throw new UserNotFoundException("You have to log in first !");
        } else {
            return userRepository.findUserByUserName(principal.getName()).orElseThrow(()
                    -> new UserNotFoundException("User not found"));
        }
    }
}
