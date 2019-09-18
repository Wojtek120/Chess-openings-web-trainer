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

    @Column(name = "branch_from_which_branched_id", nullable = false)
    private UserOpeningBranch branchFromWhichBranched;

    @OneToMany(mappedBy = "userOpeningBranch")
    private List<UserOpeningMove> userOpeningMoves= new ArrayList<>();

    @ManyToOne
    UserOpening userOpening;
}
