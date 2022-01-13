package com.kenez92.automanager.refuel;

import com.kenez92.automanager.car.CarException;
import com.kenez92.automanager.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/refuel")
class RefuelController {
    private final RefuelService refuelService;

    public RefuelController(RefuelService refuelService) {
        this.refuelService = refuelService;
    }

    @PostMapping("/add")
    public String addRefuel(@ModelAttribute("refuelDto") RefuelDto refuelDto, Principal principal) {
        try {
            refuelService.addRefuel(refuelDto, principal);
        } catch (UserNotFoundException | RefuelException | CarException e) {
            e.printStackTrace();
        }
        if (refuelDto != null && refuelDto.getCarId() != null) {
            return "redirect:/cars/" + refuelDto.getCarId();
        } else {
            return "redirect:/cars";
        }
    }

    @PostMapping("/{carId}/{refuelId}")
    public String deleteRefuel(Principal principal, @PathVariable Long carId, @PathVariable Long refuelId) {
        try {
            refuelService.deleteRefuel(principal, refuelId);
        } catch (UserNotFoundException | RefuelException e) {
            e.printStackTrace();
        }
        return "redirect:/cars/" + carId;
    }

    @PostMapping("/{carId}")
    public String editRefuel(Principal principal, @PathVariable Long carId, @ModelAttribute RefuelDto refuelDto) {
        try {
            refuelService.editRefuel(principal, refuelDto, carId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cars/" + carId;
    }
}
