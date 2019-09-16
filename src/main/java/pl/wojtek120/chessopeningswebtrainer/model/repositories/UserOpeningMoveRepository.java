package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;

public interface UserOpeningMoveRepository extends JpaRepository<UserOpeningMove, Long> {
}
