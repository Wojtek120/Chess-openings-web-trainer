package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;

public interface UserOpeningRepository extends JpaRepository <UserOpening, Long> {
}
