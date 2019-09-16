package pl.wojtek120.chessopeningswebtrainer.model.dto.user.details;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetailsDto implements Serializable {

    private String firstName;
    private String lastName;
    private String biographicalNote;
    private Integer elo;
    private Long userId;
}
