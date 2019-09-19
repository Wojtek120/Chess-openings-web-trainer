package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;

import java.util.List;

public interface UserOpeningRepository extends JpaRepository <UserOpening, Long> {
    List<UserOpening> getAllByUserUsername(String string);
}
