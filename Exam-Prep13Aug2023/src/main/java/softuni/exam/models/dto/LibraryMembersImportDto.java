package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LibraryMembersImportDto implements Serializable {

    @Expose
    @Size(min = 2, max = 40)
    private String address;
    @Expose
    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;
    @Expose
    @Size(min = 2, max = 30)
    private String lastName;
    @Expose
    @Size(min = 2, max = 20)
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
