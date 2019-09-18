package pl.wojtek120.chessopeningswebtrainer.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "openings")
@Getter @Setter
public class Opening extends BaseEntity {

    @Column(nullable = false)
    private String eco;

    @Column(name = "opening_name", nullable = false)
    private String openingName;

    private String variation;

    @Column(nullable = false, unique = true)
    private String pgn;
}
