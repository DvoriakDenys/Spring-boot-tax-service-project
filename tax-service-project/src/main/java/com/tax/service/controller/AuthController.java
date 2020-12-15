package com.tax.service.controller;

import com.tax.service.dto.UserDTO;
import com.tax.service.entity.Role;
import com.tax.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
public class AuthController implements WebMvcConfigurer {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String redirectToHomePage() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        GrantedAuthority authority = authorities.iterator().next();
        String role = authority.getAuthority();

        if (Role.CLIENT.equalsIgnoreCase(role)) {
            return "redirect:/client";
        } else if (Role.INSPECTOR.equalsIgnoreCase(role)) {
            return "redirect:/inspector";
        }
        return "error";
    }

    @GetMapping()
    public String mainPage (){
        return "signUp";
    }

    @GetMapping("/registration")
    public String registrationForm (UserDTO userDTO, Model model){
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String addClient(@Valid UserDTO userDTO, BindingResult result){
        if(result.hasErrors()) {
            return "registration";
        }
        else {
            log.info("Report payload:{}", userDTO);
            userService.saveUser(userDTO);
            return "redirect:/";
        }
    }
}
