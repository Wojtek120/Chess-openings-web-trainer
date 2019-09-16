package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
