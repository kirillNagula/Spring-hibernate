package by.nagula.controller;

import by.nagula.dto.UserDto;
import by.nagula.entity.User;
import by.nagula.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/home")
public class AuthorizationController {
    private final UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/auth")
    public ModelAndView getPage(ModelAndView modelAndView){
        modelAndView.addObject("authorization", new UserDto());
        modelAndView.setViewName("auth");
        return modelAndView;
    }

    @PostMapping(path = "/auth")
    public ModelAndView getFromForm(@Valid @ModelAttribute("authorization") UserDto userDto, BindingResult bindingResult, ModelAndView modelAndView, HttpSession session){
        if (!bindingResult.hasErrors()){
            User user = userService.find(userDto.getLogin());
            if (userService.checkPassword(user, userDto.getPassword())){
                session.setAttribute("user", user);
                modelAndView.setViewName("redirect:/home");
            } else {
                modelAndView.setViewName("/auth");
            }
        } else {
            modelAndView.setViewName("auth");
        }
        return modelAndView;
    }
}
