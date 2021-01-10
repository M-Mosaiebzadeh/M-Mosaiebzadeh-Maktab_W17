package entities;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "competition")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//@Check(constraints = "homePlayers.size() <= 11 && awayPlayers.size() <= 11")
public class Competition implements IEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_id")
    private Integer id;

    @Column(name = "season")
    private Integer season;

    @Column(name = "away_score")
    private Integer awayScore;

    @Column(name = "home_score")
    private Integer homeScore;

    @ManyToOne()
    @JoinColumn(name = "fk_home_team")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "fk_away_team")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "fk_stadium")
    private Stadium stadium;

    @ManyToMany
    @JoinTable(name = "competition_home_player",
            joinColumns = @JoinColumn(name = "competition"),
            inverseJoinColumns = @JoinColumn(name = "home_player"))
    private Set<Player> homePlayers;

    @ManyToMany
    @JoinTable(name = "competition_away_player",
            joinColumns = @JoinColumn(name = "competition"),
            inverseJoinColumns = @JoinColumn(name = "away_player"))
    private Set<Player> awayPlayers;

    @OneToMany(mappedBy = "competition")
    private Set<MatchEvent> matchEvents;

    public static CompetitionBuilder builder() {
        return new CompetitionBuilder();
    }
}
