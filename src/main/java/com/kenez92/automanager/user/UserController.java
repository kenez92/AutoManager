package com.kenez92.automanager.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createUser")
    public String createUser() {
        return "users/user";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return "redirect:createUser";
    }
}
