package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;
import softuni.exam.util.LocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement
@AccessType(AccessType.Type.FIELD)
public class BorrowingRecordsDto implements Serializable {

    @XmlElement
    @NotNull
    private BookDto book;
    @XmlElement
    @NotNull
    private LibraryMemberDto member;
    @XmlElement(name = "borrow_date")
    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate borrowDate;
    @XmlElement(name = "return_date")
    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate returnDate;

    @XmlElement
    @Size(min = 3,max = 100)
    private String remarks;
    public BookDto getBook() {
        return book;
    }

    public LibraryMemberDto getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getRemarks() {
        return remarks;
    }
}
