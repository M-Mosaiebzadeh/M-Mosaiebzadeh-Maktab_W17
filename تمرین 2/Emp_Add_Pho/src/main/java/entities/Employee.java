package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "emp_code")
    private Integer empCode;

    @Column(name = "salary")
    private Double salary;

    @OneToMany()
    @JoinColumn(name = "fk_employee")
    private Set<Address> addresses;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", empCode=" + empCode +
                ", salary=" + salary +
                '}';
    }
}
