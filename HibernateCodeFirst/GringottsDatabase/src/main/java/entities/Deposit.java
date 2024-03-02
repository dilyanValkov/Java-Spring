package entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount", scale = 2, precision = 10)
    private BigDecimal depositAmount;

    @Column(name = "deposit_interest", scale = 2, precision = 10)
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge", scale = 2, precision = 10)
    private BigDecimal depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private Boolean isDepositExpired;
}
