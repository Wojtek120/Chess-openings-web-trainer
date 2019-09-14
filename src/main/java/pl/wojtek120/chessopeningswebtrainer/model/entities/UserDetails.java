package pl.wojtek120.chessopeningswebtrainer.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
@Getter @Setter
public class UserDetails extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "biographical_note")
    private String biographicalNote;

    private Integer elo;

    @OneToOne(mappedBy = "userDetails")
    private User user;
}
