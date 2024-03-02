package com.example.demo.services;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney (BigDecimal money, Long id);
    void transferMoney (BigDecimal money, Long fromId, Long toId);
    void depositMoney (BigDecimal money, Long id);
}
