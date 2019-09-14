package pl.wojtek120.chessopeningswebtrainer.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_opening_move")
@Getter @Setter
public class UserOpeningMove extends BaseEntity {

    @Column(name = "move_number", nullable = false)
    private Integer moveNumber;

    private String comment;

    @ManyToOne
    private UserOpeningBranch userOpeningBranch;

}
