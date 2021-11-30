package com.kenez92.automanager.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/car")
class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String getCarById(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("Car", carService.getById(id, principal));
        return "car/Car";
    }

    @GetMapping("/createCar")
    public String createCar() {
        return "car/CreateCar";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createCar(@ModelAttribute("carDto") CarDto carDto, Principal principal) {
        carService.createCar(carDto, principal);
        return "redirect:createCar";
    }
}
