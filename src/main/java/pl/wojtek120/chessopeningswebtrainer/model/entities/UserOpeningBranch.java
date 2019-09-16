package pl.wojtek120.chessopeningswebtrainer.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_opening_branches")
@Getter @Setter
public class UserOpeningBranch extends BaseEntity {

    private String pgn;

    @OneToMany(mappedBy = "userOpeningBranch")
    private List<UserOpeningMove> userOpeningMoves= new ArrayList<>();

    @ManyToOne
    UserOpening userOpening;
}
