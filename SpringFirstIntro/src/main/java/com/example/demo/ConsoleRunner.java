package com.example.demo;
import com.example.demo.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;
    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.userService.register("Ivan", 37);
        this.userService.register("Donko", 25);
        this.userService.register("Petko", 45);

       User user = this.userService.findByUserName("Ivan");
       this.accountService.depositMoney(BigDecimal.TEN,2L);
       this.accountService.withdrawMoney(BigDecimal.ONE,2L);
       this.accountService.transferMoney(BigDecimal.valueOf(20), 2L, 3L);
       this.accountService.transferMoney(BigDecimal.valueOf(9), 2L, 1L);
    }

}
