package pl.wojtek120.chessopeningswebtrainer.model.dto.user.details;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserDetailsCreationDto extends UserDetailsDto {

    @Id
    private Long id;

}
