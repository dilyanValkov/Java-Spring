package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@AccessType(AccessType.Type.FIELD)
public class BookDto implements Serializable {
    @XmlElement
    @NotNull
    private String title;

    public String getTitle() {
        return title;
    }
}
