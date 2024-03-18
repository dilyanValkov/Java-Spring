package soft_uni.example.gamestoreexercise.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import soft_uni.example.gamestoreexercise.entities.models.Game;
import soft_uni.example.gamestoreexercise.entities.models.Order;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    //s email, password, full name, list of games and information whether he/she is an administrator or not
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(nullable = false,unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Game> games;

    @OneToMany(targetEntity = Order.class,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Order> orders;
}
