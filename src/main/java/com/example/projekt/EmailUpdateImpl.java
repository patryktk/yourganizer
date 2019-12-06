package com.example.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/email")
public class EmailUpdateImpl implements SettingsUpdate{

    private UserRepo userRepo;

    @Autowired
    public EmailUpdateImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public @ResponseBody
    String emailUpdate(@RequestParam Long id, @RequestParam String newEmailToUpdate, @RequestParam String newEmailToConfirm, @RequestParam String passwordToConfirm){
        if (userRepo.findById(id).get().getPassword().equals(passwordToConfirm) && newEmailToUpdate.equals(newEmailToConfirm)){
            userRepo.findById(id).get().setEmail(newEmailToUpdate);
            return "E-mail updated.";
        }
        else{
            return "Something went wrong.";
        }
    }
}
