package com.example.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/password")
public class UsernameUpdateImpl implements SettingsUpdate {

    private UserRepo userRepo;

    @Autowired
    public UsernameUpdateImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public @ResponseBody String usernameUpdate(@RequestParam Long id, @RequestParam String newUsernameToChange, @RequestParam String newUsernameToConfirm, @RequestParam String passwordToConfirm){
        if(userRepo.findById(id).get().getPassword().equals(passwordToConfirm) && newUsernameToChange.equals(newUsernameToConfirm)){
            userRepo.findById(id).get().setUsername(newUsernameToChange);
            return "Username updated.";
        }
        else{
            return "Something went wrong.";
        }
    }
}
