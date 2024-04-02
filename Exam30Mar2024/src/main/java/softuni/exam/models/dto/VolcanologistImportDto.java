package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.util.LocalDateAdapter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistImportDto {
    @XmlElement(name = "first_name")
    @Length(min = 2,max = 30)
    @NotNull
    private String firstName;

    @XmlElement(name = "last_name")
    @Length(min = 2,max = 30)
    @NotNull
    private String lastName;

    @XmlElement
    @Positive
    private Double salary;

    @XmlElement
    @Min(18)
    @Max(80)
    @NotNull
    private int age;

    @XmlElement(name = "exploring_from")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate exploringFrom;

    @XmlElement(name = "exploring_volcano_id")
    private Long exploringVolcanoId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public Long getExploringVolcanoId() {
        return exploringVolcanoId;
    }
}
