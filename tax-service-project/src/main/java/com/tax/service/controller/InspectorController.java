package com.tax.service.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InspectorController {

    @GetMapping("/inspector")
    public String main (){
        return "main-inspector";
    }

    @GetMapping("/inspector/users")
    public String showListOfUsers(){
        return "list-of-users";
    }

    @GetMapping("/inspector/reports")
    public String showReport(){
        return "reports";
    }

}
