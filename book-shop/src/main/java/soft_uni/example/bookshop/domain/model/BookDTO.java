package soft_uni.example.bookshop.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title;
    private String editionType;
    private int copies;

    @Override
    public String toString() {
        return String.format("%s %s - %d", this.title,this.editionType,this.copies);
    }
}
