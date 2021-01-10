package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stadium")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Stadium implements IEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stadium_id")
    private Integer id;

    @Column(name = "stadium_name")
    private String stadiumName;

    @Column(name = "capacity")
    private Long capacity;

    @ManyToOne()
    @JoinColumn(name = "fk_city")
    private City city;

    @OneToMany(mappedBy = "stadium")
    private Set<Competition> matches;

    public static StadiumBuilder builder() {
        return new StadiumBuilder();
    }
}
