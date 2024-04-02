package softuni.exam.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDto {
    @NotNull
    @Positive
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlElement
    private String date;

    @NotNull
    @XmlElement
    private CarBase car;

    @NotNull
    @XmlElement
    private MechanicBase mechanic;

    @NotNull
    @XmlElement
    private PartBase part;

    public BigDecimal getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public CarBase getCar() {
        return car;
    }

    public MechanicBase getMechanic() {
        return mechanic;
    }

    public PartBase getPart() {
        return part;
    }
}
