package pl.yourganizer.yourganizer.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.yourganizer.yourganizer.register.model.User;
import pl.yourganizer.yourganizer.register.repo.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepo.findByConfirmationToken(confirmationToken);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }


}
