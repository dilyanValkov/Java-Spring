package softuni.exam.models.dto.xml;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportRootDto {

    @XmlElement(name = "car")
    private List<ImportCarDto> cars;

    public List<ImportCarDto> getCars() {
        return cars;
    }
}
