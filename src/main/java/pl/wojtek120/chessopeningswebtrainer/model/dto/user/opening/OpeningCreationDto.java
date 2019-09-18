package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening;

import lombok.Data;

import javax.persistence.Id;

@Data
public class OpeningCreationDto extends OpeningDto {

    @Id
    private Long id;
}
