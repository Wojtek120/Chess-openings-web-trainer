package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch;

import lombok.Data;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserOpeningBranchDto implements Serializable {

    private String pgn;
    private Integer moveNoFromWhichBranched;
    private UserOpeningBranch branchFromWhichBranched;
    private List<Long> userOpeningMovesId = new ArrayList<>();
    private UserOpening userOpening;

}
