package com.example.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationExceptions(MethodArgumentNotValidException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", ex.getBindingResult().getFieldErrors());
        modelAndView.setViewName("errorPage");
        return modelAndView;
    }
}
