package pl.wojtek120.chessopeningswebtrainer.model.entities;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_openings")
@Getter @Setter
public class UserOpening extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = Boolean.FALSE;

    @Column(name = "played_as", nullable = false)
    private String playedAs;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userOpening")
    private List<UserOpeningBranch> userOpeningBranches = new ArrayList<>();
}
