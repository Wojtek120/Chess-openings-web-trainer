package pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class OpeningDto {

    @NotBlank
    private String eco;
    @NotBlank
    private String openingName;
    private String variation;
    @NotBlank
    private String pgn;

}
