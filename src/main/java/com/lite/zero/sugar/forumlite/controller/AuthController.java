package com.lite.zero.sugar.forumlite.controller;

import com.lite.zero.sugar.forumlite.entity.UserEntity;
import com.lite.zero.sugar.forumlite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserEntity user,
                           BindingResult bindingResult, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.save(user);
        return "redirect:/api/forum?success";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


}
