package pl.yourganizer.login.repo;

import org.springframework.data.repository.CrudRepository;
import pl.yourganizer.login.model.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
