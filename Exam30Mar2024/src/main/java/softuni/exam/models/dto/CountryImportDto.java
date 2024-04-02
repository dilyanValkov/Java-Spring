package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CountryImportDto implements Serializable {
    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String name;
    @Expose
    @Length(min = 3, max = 30)
    private String capital;

    public String getCapital() {
        return capital;
    }

    public String getName() {
        return name;
    }


}
