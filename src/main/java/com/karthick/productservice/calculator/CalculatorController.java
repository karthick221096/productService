package com.karthick.productservice.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
    private final CalculatorService calculatorService;
    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b){
        return calculatorService.add(a,b);
    }
}
