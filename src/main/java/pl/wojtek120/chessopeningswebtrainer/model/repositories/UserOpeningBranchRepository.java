package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

import java.util.List;

public interface UserOpeningBranchRepository extends JpaRepository<UserOpeningBranch, Long> {
    List<UserOpeningBranch> getAllByUserOpeningId(Long id);

    @Query("SELECT DISTINCT p FROM UserOpeningBranch p LEFT JOIN FETCH p.userOpeningMoves WHERE p.userOpening.id = ?1")
    List<UserOpeningBranch> getAllByOpeningIdWithMoves(Long id);

    void deleteAllByUserOpeningId(Long id);
}
