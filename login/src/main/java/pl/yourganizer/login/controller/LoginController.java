package pl.yourganizer.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping({"/","/login"})
    public String index(){
        return "index";
    }

    @GetMapping({"/menu"})
    public String hello(){
        return "menu";
    }

    @GetMapping({"/user"})
    public String user(){
        return "user";
    }
}
