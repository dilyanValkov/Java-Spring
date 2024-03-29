package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    @Column(name = "product_id")
    private long productId;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "store_location_id")
    private long storeLocationId;

    @Column
    private Date date;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StoreLocation storeLocation;
}
