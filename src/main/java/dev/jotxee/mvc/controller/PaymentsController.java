package dev.jotxee.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Controller
public class PaymentsController {

    @GetMapping("/monthly-payments")
    public String getMonthlyPayments(final ModelMap modelMap) { // Usando ModelMap
        modelMap.addAllAttributes(Map.of(
                        "title", "Histórico de pagos",
                        "metaDescription", "Gestión integral de la comunidad"
                )
        );

        return "monthly-payments";
    }

    @GetMapping("/monthly-payments/{id}")
    public ModelAndView getMonthlyPaymentDetails(final ModelAndView modelView, @PathVariable final String id) { // Usando ModelAndView
        modelView.addAllObjects(Map.of(
                        "title", "Detalle del pago",
                        "metaDescription", "Gestión integral de la comunidad",
                        "paymentId", id
                )
        );

        modelView.setViewName("monthly-payments-detail");
       return modelView;
    }
}
