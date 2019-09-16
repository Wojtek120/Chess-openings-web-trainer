package pl.wojtek120.chessopeningswebtrainer.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

public interface UserOpeningBranchRepository extends JpaRepository<UserOpeningBranch, Long> {
}
