package com.karthick.productservice.controllerAdvice;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage("Getting null pointer exception");
        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProdcutNotFoundExceptionHandler(ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage(productNotFoundException.getMessage());
        return errorDto;
    }
}
