package com.cp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cp.DTO.UserLoginDTO;
import com.cp.Service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
        @Autowired
        private UserService userService;

        @ModelAttribute("user")
        public UserLoginDTO userLoginDTO(){
            return new UserLoginDTO();
        }

        @GetMapping
        public String login(){
            return "Login";
        }

        @PostMapping
        public void loginUser(@ModelAttribute("user")
        UserLoginDTO userLoginDTO){
            userService.loadUserByUsername(userLoginDTO.getUsername());
        }
}
