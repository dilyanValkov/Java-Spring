package entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "magic_wands")
public abstract class MagicWand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private int wandSize;
}
