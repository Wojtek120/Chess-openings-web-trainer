package pl.wojtek120.chessopeningswebtrainer.model.dto.user;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserCreationDto extends UserDto {
    @Id
    private Long id;
}
