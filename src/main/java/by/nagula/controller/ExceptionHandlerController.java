package by.nagula.controller;

import by.nagula.exception.UserAlreadyExistException;
import by.nagula.exception.UserNotExistInDbException;
import by.nagula.exception.WrongLginException;
import by.nagula.exception.WrongPasswordException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExistException.class)
    public String userContainsInDb(Model model, UserAlreadyExistException ex){
        model.addAttribute("message", ex.getMessage());
        return "reg";
    }

    @ExceptionHandler(WrongPasswordException.class)
    public String wrongPassword(Model model, WrongPasswordException ex){
        model.addAttribute("pasMes", ex.getMessage());
        return "auth";
    }

    @ExceptionHandler(UserNotExistInDbException.class)
    public String noUser(Model model, UserNotExistInDbException ex){
        model.addAttribute("logMes", ex.getMessage());
        return "auth";
    }

    @ExceptionHandler(WrongLginException.class)
    public String wrongLogin(Model model, WrongLginException ex){
        model.addAttribute("logMes", ex.getMessage());
        return "account";
    }
}
