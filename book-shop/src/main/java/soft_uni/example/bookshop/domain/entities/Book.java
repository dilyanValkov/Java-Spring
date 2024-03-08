package soft_uni.example.bookshop.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import soft_uni.example.bookshop.domain.enums.AgeRestriction;
import soft_uni.example.bookshop.domain.enums.EditionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity (name = "books")
public class Book extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column
    private Integer copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public String getBookTitleAndPriceFormat(){
        return this.title + " - $" + this.price;
    }

    public String getBookTitleEditionTypeAndPrice(){
        return this.title + " " + this.editionType.name() + " " + this.price;
    }



}
