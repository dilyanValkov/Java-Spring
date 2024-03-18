package soft_uni.example.jsonprocessing.domain.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @Min(3)
    private String lastName;

    @Column
    private int age;

    @OneToMany(targetEntity = Product.class,mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Product> sellingProducts;

    @OneToMany(targetEntity = Product.class,mappedBy = "buyer",fetch = FetchType.EAGER)
    private Set<Product> boughtProducts;

    @ManyToMany
    private Set<User> friends;
}
