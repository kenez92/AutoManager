package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/fuel")
class FuelController {
    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping("/add")
    public String addFuel(@ModelAttribute("fuelDto") FuelDto fuelDto, Principal principal) {
        try {
            fuelService.addFuel(fuelDto, principal);
        } catch (UserNotFoundException | FuelException | CarException e) {
            e.printStackTrace();
        }
        if (fuelDto != null && fuelDto.getCarId() != null) {
            return "redirect:/cars/" + fuelDto.getCarId();
        } else {
            return "redirect:/cars";
        }
    }

    @PostMapping("/{carId}/{fuelId}")
    public String deleteFuel(Principal principal, @PathVariable Long carId, @PathVariable Long fuelId) {
        try {
            fuelService.deleteFuel(principal, fuelId);
        } catch (UserNotFoundException | FuelException e) {
            e.printStackTrace();
        }
        return "redirect:/cars/" + carId;
    }
}
