package pl.yourganizer.yourganizer.register.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.yourganizer.yourganizer.register.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}