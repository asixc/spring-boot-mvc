package dev.jotxee.mvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping({"/", "", "/profile"})
    public String index(final Model model) { // Usando Modelo
        model.addAllAttributes(Map.of(
                        "title", "Datos de usuario",
                        "metaDescription", "Gestión integral de la comunidad"
                )
        );

        return "user-profile";
    }
}
