package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private int price;

    @OneToMany
    @JoinColumn
    private Set<Sale> sales;

}
