package by.nagula.controller;

import by.nagula.entity.User;
import by.nagula.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/account")
public class UserPropertiesController {
    private final UserService userService;

    public UserPropertiesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getPage(ModelAndView modelAndView, HttpSession session){
        modelAndView.addObject("properties", new User());
        modelAndView.setViewName("account");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postPage(@ModelAttribute("properties") @Valid User user, BindingResult bindingResult, ModelAndView modelAndView, HttpSession session){
        if (!bindingResult.hasErrors()){
            User user1 = (User) session.getAttribute("user");
            user.setId(user1.getId());
            userService.updateUser(user);
            session.setAttribute("user", user);
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("account");
        }
        return modelAndView;
    }

    @PostMapping(path = "/{id}")
    public ModelAndView deletePage(@PathVariable int id, ModelAndView modelAndView, HttpSession session){
        System.out.println("удалем " + id);
        userService.deleteUser(id);
        session.invalidate();
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
