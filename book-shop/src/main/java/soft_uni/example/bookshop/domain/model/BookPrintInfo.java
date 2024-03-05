package soft_uni.example.bookshop.domain.model;

import soft_uni.example.bookshop.domain.enums.AgeRestriction;
import soft_uni.example.bookshop.domain.enums.EditionType;
import java.math.BigDecimal;

public class BookPrintInfo {
    private String title;

    private EditionType editionType;

    private AgeRestriction ageRestriction;

    private BigDecimal price;

    public BookPrintInfo (String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price){
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.title + " "
                + this.editionType + " "
                + this.ageRestriction + " "
                + this.price;
    }
}
