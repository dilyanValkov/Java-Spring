package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
@XmlRootElement(name = "borrowing_records")
@AccessType(AccessType.Type.FIELD)
public class BorrowingRecordsRootDto implements Serializable {
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordsDto> borrowingRecordsDto;

    public List<BorrowingRecordsDto> getBorrowingRecordsDtoSet() {
        return borrowingRecordsDto;
    }
}
