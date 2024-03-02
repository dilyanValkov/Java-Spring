package entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{
    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @OneToMany
    private Set<Sale> sales;
}
