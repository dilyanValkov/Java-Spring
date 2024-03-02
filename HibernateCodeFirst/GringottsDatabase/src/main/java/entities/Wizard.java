package entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wizards")
public abstract class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column
    private String notes;

    @Column(nullable = false)
    private int age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(name = "wizard_deposits")
    private List <Deposit> deposits;
}
