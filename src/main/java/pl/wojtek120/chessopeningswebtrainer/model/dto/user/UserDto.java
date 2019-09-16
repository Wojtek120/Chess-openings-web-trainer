package pl.wojtek120.chessopeningswebtrainer.model.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto implements Serializable {

    @NotBlank
    @Length(min = 5, max = 30)
    private String username;

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotBlank
    @Email
    private String email;

    private Boolean enabled;

    private UserDetails userDetailsId;

    private Set<String> roles = new HashSet<>();

    private List<Long> userOpeningBranches = new ArrayList<>();

}
