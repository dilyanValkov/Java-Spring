package soft_uni.example.gamestoreexercise.repositories;

import soft_uni.example.gamestoreexercise.entities.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
