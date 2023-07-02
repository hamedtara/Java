package com.example.aecom.controller;

import com.example.aecom.service.CalculationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public String calculateForm(Model model) {
        model.addAttribute("input", 0);
        model.addAttribute("result", "");
        return "calculate";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double input, Model model) {
        double result = calculationService.calculate(input);
        model.addAttribute("input", input);
        model.addAttribute("result", result);
        return "calculate";
    }
}
