package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contract")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Contract implements IEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "season")
    private Date season;

    @Column(name = "salary")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_team")
    private Team team;


    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    @Override
    public String toString() {
        return "Contract{" +
                "season=" + season +
                ", salary=" + salary +
                '}';
    }
}