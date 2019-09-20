package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening;

import lombok.Data;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch.UserOpeningBranchDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserOpeningDto implements Serializable {

    @Id
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean isPublic;
    @NotBlank
    private String playedAs;
    private Long userId;
    private List<Long> userOpeningBranchIds = new ArrayList<>();

    public void addUserOpeningBranch(Long userOpeningBranchId) {
        userOpeningBranchIds.add(userOpeningBranchId);
    }

}
