package com.ifce.frontms.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceAccessException.class, HttpServerErrorException.class})
    public ModelAndView handleServiceUnavailable(Exception ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 502);
        mav.addObject("mensagem", "😿 Algo deu errado my friend. Tente novamente mais tarde.");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 500);
        mav.addObject("mensagem", "Desculpe, ocorreu um erro inesperado. Tente novamente mais tarde.");
        return mav;
    }
}