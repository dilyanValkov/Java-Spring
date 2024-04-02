package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{

    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Timestamp date;
    @ManyToOne()
    @JoinColumn(name = "mechanic_id",referencedColumnName = "id")
    private Mechanic mechanic;
    @ManyToOne()
    @JoinColumn(name = "parts_id",referencedColumnName = "id")
    private Part part;

    @ManyToOne()
    @JoinColumn(name = "cars_id",referencedColumnName = "id")
    private Car car;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
