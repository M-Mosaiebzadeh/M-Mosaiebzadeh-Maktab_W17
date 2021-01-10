package entities;

import entities.enums.MatchEventType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "match_event")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MatchEvent implements IEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_event_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MatchEventType type;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "fk_competition")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "fk_player")
    private Player player;

    public static MatchEventBuilder builder() {
        return new MatchEventBuilder();
    }
}
