package softuni.exam.models.dto;

import softuni.exam.util.LocalDateAdapter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerDto {

    @XmlElement(name = "average_observation_hours")
    @Min(500)
    private double averageObservationHours;

    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDay;
    @XmlElement(name = "first_name")
    @Size(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    private String lastName;

    @XmlElement
    @Min(15000)
    private double salary;

    @XmlElement(name = "observing_star_id")
    private Long observingStarId;

    public double getAverageObservationHours() {
        return averageObservationHours;
    }


    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public Long getObservingStarId() {
        return observingStarId;
    }
}
