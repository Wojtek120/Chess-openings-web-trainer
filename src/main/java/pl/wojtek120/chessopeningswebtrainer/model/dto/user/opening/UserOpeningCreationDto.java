package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserOpeningCreationDto extends UserOpeningDto {
    @Id
    private Long id;
}
