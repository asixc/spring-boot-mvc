package dev.jotxee.mvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping({"/", "/dashboard"})
    public String getDashboard(final Model model){
        return "dashboard";
    }
}


