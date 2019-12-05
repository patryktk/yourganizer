package pl.yourganizer.yourganizer.register.service;

import org.springframework.stereotype.Service;
import pl.yourganizer.yourganizer.register.repo.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }


}
