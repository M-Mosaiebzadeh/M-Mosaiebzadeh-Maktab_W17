package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class City implements IEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city")
    private Set<Team> teams;

    @OneToMany(mappedBy = "city")
    private Set<Stadium> stadiums;

    public static CityBuilder builder() {
        return new CityBuilder();
    }

}
