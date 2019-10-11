package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.Opening;

import java.util.Optional;

public interface OpeningRepository extends JpaRepository<Opening, Long> {

    Opening getOpeningByPgn(String name);
}
