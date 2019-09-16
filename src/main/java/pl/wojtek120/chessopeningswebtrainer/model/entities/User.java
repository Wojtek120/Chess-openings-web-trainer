package pl.wojtek120.chessopeningswebtrainer.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.wojtek120.chessopeningswebtrainer.model.entities.embeddable.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean enabled = Boolean.FALSE;

    @ElementCollection
    @CollectionTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<UserOpening> userOpenings = new ArrayList<>();

    @OneToOne
    private UserDetails userDetails;

}
