package dev.jotxee.mvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping({"", "/", "/dashboard"})
    public String getDashboard(final Model model){
        model.addAllAttributes(Map.of(
                        "title", "Dashboard",
                        "metaDescription", "Gestión integral de la comunidad"
                )
        );
        return "dashboard";
    }
}


