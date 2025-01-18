package com.karthick.productservice.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculatorControllerTest {

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private CalculatorController calculatorController;
    @Test
    public void returnIntegerWhenTwoArgumentPassed(){
        when(calculatorService.add(5,10)).thenReturn(15);

        int a = 5;
        int b = 10;
        int expectedResult = 15;

        int result = calculatorController.add(a,b);

        Assertions.assertEquals(expectedResult,result);
    }
}
