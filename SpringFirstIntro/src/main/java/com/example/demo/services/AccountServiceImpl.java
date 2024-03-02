package com.example.demo.services;
import jakarta.transaction.Transactional;
import com.example.demo.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.AccountRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

        Account account = accountRepository.findAccountById(id)
                .orElseThrow(() -> new RuntimeException("Account is not present"));

        BigDecimal balance = account.getBalance();

        if (money.compareTo(balance) > 0){
            throw new RuntimeException("You are trying to withdraw more money than balance");
        }
        account.setBalance(balance.subtract(money));
        accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Optional<Account> account= this.accountRepository.findAccountById(id);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        BigDecimal balance = account.get().getBalance();
        BigDecimal balanceWithAddedMoney = balance.add(money);
        account.get().setBalance(balanceWithAddedMoney);
        this.accountRepository.save(account.get());
    }

    @Override
    @Transactional
    public void transferMoney(BigDecimal money, Long fromId, Long toId) {
        Optional<Account> fromAccount = this.accountRepository.findAccountById(fromId);
        Optional<Account> toAccount = this.accountRepository.findAccountById(toId);
        BigDecimal balance = fromAccount.get().getBalance();
        if (balance.compareTo(money) < 0){
            throw new RuntimeException("Not enough balance");
        }
        fromAccount.get().setBalance(balance.subtract(money));
        this.accountRepository.save(fromAccount.get());

        toAccount.get().setBalance(toAccount.get().getBalance().add(money));
    }
}
