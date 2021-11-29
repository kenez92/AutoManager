package com.kenez92.automanager.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public String getUser(Model model, @PathVariable String userName) {
        UserDto userDto = userService.getUserByUserName(userName);
        model.addAttribute("user", userDto);
        return ""; //todo temporary return empty value
    }

    @PostMapping("/createUser")
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return createdUser;
    }
}
