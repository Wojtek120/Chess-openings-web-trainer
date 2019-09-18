package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch;

import lombok.Data;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserOpeningBranchDto implements Serializable {

    private Integer branchNumber;
    private Integer parentBranchNumber;
    private List<Long> userOpeningMovesIds = new ArrayList<>();
    private Long userOpeningId;

    public UserOpeningBranchDto() {
    }

    public UserOpeningBranchDto(Integer branchNumber, Integer parentBranchNumber, Long userOpeningId) {
        this.branchNumber = branchNumber;
        this.parentBranchNumber = parentBranchNumber;
        this.userOpeningId = userOpeningId;
    }

    public void add(Long userOpeningMoveId) {
        userOpeningMovesIds.add(userOpeningMoveId);
    }
}
