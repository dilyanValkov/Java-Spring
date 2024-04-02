package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

public class VolcanoImportDto implements Serializable {
    @Expose
    @Length(min = 2, max = 30)
    @NotNull
    private String name;
    @Expose
    @Positive
    @NotNull
    private int elevation;
    @Expose
    private String volcanoType;
    @Expose
    @NotNull
    private Boolean isActive;
    @Expose
    private String lastEruption;
    @Expose
    private Long country;

    public String getName() {
        return name;
    }

    public int getElevation() {
        return elevation;
    }

    public String getVolcanoType() {
        return volcanoType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public Long getCountry() {
        return country;
    }
}
