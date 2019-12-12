package pl.yourganizer.yourganizer.register.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.yourganizer.yourganizer.register.model.User;
import pl.yourganizer.yourganizer.register.service.UserService;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

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
    if (user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
    {
        Random random = new Random();
        int round = random.nextInt(20);
        String encoded = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(round));
        user.setPassword(encoded);
        userService.getUserRepo().save(user);
    List<User> noteList = userService.getUserRepo().findAll();
    model.addAttribute("user", noteList);
    return "signup";
    }
    else
    {
        userService.getUserRepo().delete(user);
        return "showusers";
    }

    }

}
