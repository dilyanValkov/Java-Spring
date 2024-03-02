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
@Table(name = "store_location")
public class StoreLocation extends BaseEntity {

    @Column(name = "location_name" )
    private String name;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;
}
