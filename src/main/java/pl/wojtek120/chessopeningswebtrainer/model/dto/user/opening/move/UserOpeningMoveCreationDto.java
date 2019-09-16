package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserOpeningMoveCreationDto extends UserOpeningMoveDto {

    @Id
    private Long id;

}
