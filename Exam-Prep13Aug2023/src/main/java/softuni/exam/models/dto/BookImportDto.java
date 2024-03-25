package softuni.exam.models.dto;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BookImportDto implements Serializable {
    @Expose
    @Size(min = 3,max = 40)
    private String author;
    @Expose
    private boolean available;
    @Expose
    @Size(min = 5)
    private String description;
    @Expose
    private String genre;
    @Expose
    @Size(min = 3,max = 40)
    private String title;
    @Expose
    @Positive
    private double rating;

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }
}
