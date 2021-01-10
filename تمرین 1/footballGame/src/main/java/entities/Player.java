package entities;

import lombok.*;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.util.List;
import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Player extends User{

    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "player")
    private Set<MatchEvent> matchEvents;

    @ManyToMany(mappedBy = "homePlayers")
    private Set<Competition> homeCompetition;

    @ManyToMany(mappedBy = "awayPlayers")
    private Set<Competition> awayCompetition;

}
