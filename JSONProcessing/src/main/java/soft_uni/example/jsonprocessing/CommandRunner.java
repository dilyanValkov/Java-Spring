package soft_uni.example.jsonprocessing;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.example.jsonprocessing.services.seed.SeedService;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService service;

@Autowired
    public CommandRunner(SeedService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        this.service.seedAll();
    }
}
