package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{

    @Column
    private Date date;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StoreLocation storeLocation;
}
