package com.kenez92.automanager.car;

import com.kenez92.automanager.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cars")
class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String getCarById(Model model, Principal principal, @PathVariable Long id) {
        try {
            model.addAttribute("car", carService.getById(id, principal));
            return "cars/car";
        } catch (UserNotFoundException ex) {
            return "user/login?error";
        } catch (CarException ex) {
            return "cars/car"; //todo temporary return createCar
        }
    }

    @GetMapping("/createCar")
    public String createCar() {
        return "cars/createCar";
    }

    @PostMapping
    public String createCar(@ModelAttribute("carDto") CarDto carDto, Principal principal) {
        try {
            carService.createCar(carDto, principal);
            return "redirect:createCar";
        } catch (UserNotFoundException ex) {
            return "user/login?error";
        } catch (CarException ex) {
            return "redirect:createCar"; //todo temporary return createCar
        }
    }

    @GetMapping
    public String getCars(Model model, Principal principal) {
        try {
            List<CarDto> cars = carService.getCarsByPrincipal(principal);
            model.addAttribute("cars", cars);
            return "cars/cars";
        } catch (UserNotFoundException ex) {
            return "login/login";
        }
    }
}
