package pl.yourganizer.yourganizer.register.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.yourganizer.yourganizer.register.model.User;
import pl.yourganizer.yourganizer.register.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) { this.userService = userService; }

    @GetMapping("/register")
    public String add(User user, Model model){
    return"signup";
    }
    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
    if(result.hasErrors()){
    return "signup";
    }
    userService.getUserRepo().save(user);
    List<User> noteList = userService.getUserRepo().findAll();
    model.addAttribute("user", noteList);
    return "showusers";

    }

}
