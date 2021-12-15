package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.user.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/fuel")
class FuelController {
    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping("/{id}")
    public FuelDto getFuelById(@PathVariable Long id) {
        return fuelService.getById(id);
    }

    @PostMapping("/add")
    public void addFuel(@ModelAttribute("fuelDto") FuelDto fuelDto, Principal principal) {
        try {
            fuelService.addFuel(fuelDto, principal);
        } catch (UserNotFoundException ex) {

        } catch (FuelException e) {
            e.printStackTrace();
        } catch (CarException e) {
            e.printStackTrace();
        }

    }
}
