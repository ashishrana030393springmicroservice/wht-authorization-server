package com.user.auth.controller;

import com.user.auth.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("login")
    public String login(){
        return  "login";
    }

}
