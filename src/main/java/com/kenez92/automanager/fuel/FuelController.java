package com.kenez92.automanager.fuel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
