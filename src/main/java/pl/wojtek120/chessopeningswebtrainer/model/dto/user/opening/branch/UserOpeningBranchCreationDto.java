package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch;

import javax.persistence.Id;

public class UserOpeningBranchCreationDto extends UserOpeningBranchDto {

    @Id
    private Long id;

}
