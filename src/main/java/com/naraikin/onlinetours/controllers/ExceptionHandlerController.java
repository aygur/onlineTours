package com.naraikin.onlinetours.controllers;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dmitrii on 11.03.17.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = Logger.getLogger(ExceptionHandlerController.class);
    public static final String DEFAULT_ERROR_VIEW = "error";
    public static final String DEFAULT_404_ERROR_VIEW = "error404";

    @ExceptionHandler({NoHandlerFoundException.class,})
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_404_ERROR_VIEW);
        logger.error(e);
        return mav;
    }
}
