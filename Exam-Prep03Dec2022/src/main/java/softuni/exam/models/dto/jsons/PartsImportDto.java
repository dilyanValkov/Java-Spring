package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PartsImportDto implements Serializable {
    @Expose
    @Size(min = 2, max = 19)
    private String partName;
    @Expose
    @Min(10)
    @Max(2000)
    private double price;
    @Expose
    @Positive
    private int quantity;

    public String getPartName() {
        return partName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
