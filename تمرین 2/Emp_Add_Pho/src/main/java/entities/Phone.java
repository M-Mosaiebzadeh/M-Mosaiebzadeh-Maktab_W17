package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "mob_number")
    private String mobNumber;
}
