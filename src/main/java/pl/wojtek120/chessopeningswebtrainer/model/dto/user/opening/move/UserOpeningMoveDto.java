package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move;

import lombok.Data;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch.UserOpeningBranchDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserOpeningMoveDto implements Serializable {

    @NotNull
    private Integer moveNumber;
    private String comment;
    private String pgn;
    private Long userOpeningBranchId;

    public UserOpeningMoveDto() {
    }

    public UserOpeningMoveDto(@NotNull Integer moveNumber, String comment, String pgn, Long userOpeningBranchId) {
        this.moveNumber = moveNumber;
        this.comment = comment;
        this.pgn = pgn;
        this.userOpeningBranchId = userOpeningBranchId;
    }
}
