package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@AccessType(AccessType.Type.FIELD)
public class LibraryMemberDto implements Serializable {
    @XmlElement
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }
}
