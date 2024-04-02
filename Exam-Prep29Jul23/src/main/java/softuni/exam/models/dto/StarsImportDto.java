package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class StarsImportDto implements Serializable {

    @Expose
    @Size(min = 6)
    private String description;
    @Expose
    @Positive
    private float lightYears;
    @Expose
    @Size(min = 2, max = 30)
    private String name;
    @Expose
    private String starType;
    @Expose
    private Long constellation;

    public String getDescription() {
        return description;
    }

    public float getLightYears() {
        return lightYears;
    }

    public String getName() {
        return name;
    }

    public String getStarType() {
        return starType;
    }

    public Long getConstellation() {
        return constellation;
    }
}
