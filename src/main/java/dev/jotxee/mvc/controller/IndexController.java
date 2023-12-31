package dev.jotxee.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(final Model model) { // Usando Modelo
        model.addAllAttributes(Map.of(
                "title", "Comunidad",
                "metaDescription", "Gestión integral de la comunidad"
                )
        );

        return "index";
    }

    /** Login form. */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /** Login form. */
    @GetMapping("/register")
    public String register() {
        return "registerForm";
    }

    /** Error page. */
    @GetMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @GetMapping("/about")
    public String getAbout(final Map<String, Object> map) { // Usando Map directamente
        map.putAll(Map.of(
                "title", "Sobre esta web",
                "metaDescription", "Gestión integral de la comunidad"
                )
        );

        return "about";
    }

}
