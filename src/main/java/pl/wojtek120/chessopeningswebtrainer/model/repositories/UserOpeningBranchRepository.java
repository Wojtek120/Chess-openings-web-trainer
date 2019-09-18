package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

import java.util.List;

public interface UserOpeningBranchRepository extends JpaRepository<UserOpeningBranch, Long> {
    List<UserOpeningBranch> getAllByUserOpeningId(Long id);
    void deleteAllByUserOpeningId(Long id);
}
