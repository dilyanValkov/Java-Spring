package soft_uni.example.gamestoreexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.gamestoreexercise.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
