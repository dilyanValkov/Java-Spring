package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    @Column(unique = true,nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    private boolean available;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String tittle) {
        this.title = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
