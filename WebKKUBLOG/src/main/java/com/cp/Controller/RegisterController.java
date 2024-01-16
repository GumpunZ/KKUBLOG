package com.cp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cp.DTO.UserRegisterDTO;
import com.cp.Service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegisterDTO userRegistrationDto() {
        return new UserRegisterDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegisterDTO registerDTO) {
        userService.save(registerDTO);
        return "redirect:/login";
    }
}
