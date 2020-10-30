package by.nagula.controller;

import by.nagula.entity.User;
import by.nagula.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/home")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping (path = "/save")
    public void save(User user){
        System.out.println(user);
        userService.save(user);
    }

    @GetMapping (path = "/find")
    public void find(@RequestParam String login){
        System.out.println(login);
        User user = userService.find(login);
        System.out.println(user);
    }
}
