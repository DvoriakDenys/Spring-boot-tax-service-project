package com.tax.service.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/inspector")
public class InspectorController {

    @GetMapping()
    public String main (){
        return "inspector/main-inspector";
    }

    @GetMapping("/users")
    public String showListOfUsers(){
        return "inspector/list-of-users";
    }

    @GetMapping("/report")
    public String showReport(){
        return "inspector/report-inspector";
    }

}
