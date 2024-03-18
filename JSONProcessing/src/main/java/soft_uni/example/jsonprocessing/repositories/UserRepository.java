package soft_uni.example.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft_uni.example.jsonprocessing.domain.entites.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from `product_shop`.users ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Optional<User> getRandomEntity();
}

