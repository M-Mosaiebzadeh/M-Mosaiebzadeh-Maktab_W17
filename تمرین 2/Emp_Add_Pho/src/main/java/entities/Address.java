package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "city")
    private String city;

    @OneToMany
    @JoinColumn(name = "fk_address")
    private Set<Phone> phones;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}
