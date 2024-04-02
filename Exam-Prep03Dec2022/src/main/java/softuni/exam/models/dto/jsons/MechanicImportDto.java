package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class MechanicImportDto implements Serializable {

   @Expose
   @Size(min = 2)
    private String firstName;

    @Expose
    @Size(min = 2)
    private String lastName;
    @Expose
    @Email
    private String email;

    @Expose
    @Size(min = 2)
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
