package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Team implements IEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer id;

    @Column(name = "team_name")
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "fk_city")
    private City city;

    @OneToMany(mappedBy = "team")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Competition> homeCompetition;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Competition> awayCompetition;


    public static TeamBuilder builder() {
        return new TeamBuilder();
    }

}
