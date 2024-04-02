package softuni.exam.models.entity;

import softuni.exam.models.enums.volcanoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity{
    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private int elevation;

    @Column(name = "volcano_type")
    @Enumerated(EnumType.STRING)
    private volcanoType volcanoType;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "last_eruption")
    private LocalDate lastEruption;
    @OneToMany(mappedBy = "volcano")
    private List<Volcanologist> volcanologists;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public softuni.exam.models.enums.volcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(softuni.exam.models.enums.volcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public List<Volcanologist> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(List<Volcanologist> volcanologists) {
        this.volcanologists = volcanologists;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
