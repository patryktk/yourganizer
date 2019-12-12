package pl.yourganizer.yourganizer.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.yourganizer.yourganizer.register.model.User;
import pl.yourganizer.yourganizer.register.service.EmailService;
import pl.yourganizer.yourganizer.register.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class RegisterController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public RegisterController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String add(User user, Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        User userExist = userService.findByEmail(user.getEmail());
        if (userExist != null) {
            result.reject("email");
            return "showusers";
        } else {

            user.setEnabled(false);
            user.setConfirmationToken(UUID.randomUUID().toString());
            userService.getUserRepo().save(user);
            String appUrl = "localhost://8080";
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Register Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("projectexample6@gmail.com");
            emailService.sendEmail(registrationEmail);
            if (user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                Random random = new Random();
                int round = random.nextInt(20);
                String encoded = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(round));
                user.setPassword(encoded);
                userService.getUserRepo().save(user);
                List<User> noteList = userService.getUserRepo().findAll();
                model.addAttribute("user", noteList);
                return "signup";
            } else {
                userService.getUserRepo().delete(user);
                return "showusers";
            }

        }
    }

    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam("token") String token, User user, Model model) {

        user = userService.findByConfirmationToken(token);
        if (user == null) {
            return "register";
        } else {
            user = userService.findByConfirmationToken(user.getConfirmationToken());
            user.setEnabled(true);
            userService.saveUser(user);
            return "calendar";
        }

    }
}