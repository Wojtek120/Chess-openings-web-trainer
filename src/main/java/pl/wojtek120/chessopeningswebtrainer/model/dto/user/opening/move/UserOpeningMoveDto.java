package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserOpeningMoveDto implements Serializable {

    @NotNull
    private Integer moveNumber;
    private String comment;
    private Long userOpeningBranchId;

}
