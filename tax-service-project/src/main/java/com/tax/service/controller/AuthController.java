package com.tax.service.controller;

import com.tax.service.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class AuthController {

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
}
