package com.karthick.productservice.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public Integer add(int a, int b){
        System.out.println("some logic");
        System.out.println("some more logic");
        int result = a+b;
        System.out.println("some more logic after add ");
        System.out.println("some more logic after add ");
        return result;
    }
}
