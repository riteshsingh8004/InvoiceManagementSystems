package com.invoice.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public RedirectView handleNoResourceFoundException(NoResourceFoundException ex) {
        // Log the exception (if needed)
        ex.printStackTrace();
        // Redirect to a custom error page or handle as needed
        return new RedirectView("/error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        // Log the exception (if needed)
        ex.printStackTrace();
        // Redirect to the error page
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
        
        
        
    }
    
}