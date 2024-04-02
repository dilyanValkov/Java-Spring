package softuni.exam.models.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "light_years",nullable = false)
    private float lightYears;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "star_type",nullable = false)
    private StarType starType;
    @ManyToOne
    @JoinColumn(name = "constellation_id", referencedColumnName = "id")
    private Constellation constellation;

    @OneToMany(mappedBy = "observingStar")
    private Set<Astronomer> observers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLightYears() {
        return lightYears;
    }

    public void setLightYears(float lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public Set<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Astronomer> observers) {
        this.observers = observers;
    }
}
