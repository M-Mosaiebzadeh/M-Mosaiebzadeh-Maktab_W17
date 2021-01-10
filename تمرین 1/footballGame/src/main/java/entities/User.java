package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements IEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Integer id;

    @Column(name = "first_name")
    protected String firstname;

    @Column(name = "last_name")
    protected String lastname;

    @OneToMany(mappedBy = "user")
    private Set<Contract> contracts;

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contracts=" + contracts +
                '}';
    }
}
