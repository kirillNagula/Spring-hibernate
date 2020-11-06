package by.nagula.controller;

import by.nagula.entity.User;
import by.nagula.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/home")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/reg")
    public ModelAndView showPage(ModelAndView modelAndView){
        modelAndView.addObject("registration", new User());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping (path = "/reg")
    public ModelAndView getFromForm(@Valid @ModelAttribute("registration") User user, BindingResult bindingResult, ModelAndView modelAndView){
        if(!bindingResult.hasErrors()){
            System.out.println(user);
            userService.save(user);
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("reg");
        }
        return modelAndView;
    }
}
