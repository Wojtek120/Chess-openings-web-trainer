package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserOpeningBranchDto implements Serializable {

    private String pgn;
    private List<Long> userOpeningMovesId = new ArrayList<>();
    Long userOpeningId;

}
