package soft_uni.example.gamestoreexercise.repositories;

import soft_uni.example.gamestoreexercise.entities.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
