package bankkata.example.bankkata.service;


import bankkata.example.bankkata.model.*;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.util.List;



@Service
public class AccountService {
    private Account account = new Account();

    public void deposit (BigDecimal amount) {
        account.deposit(amount);
    }

    public void withdraw(BigDecimal amount){
        account.withdraw(amount);
    }

    public List<Operation> getStatement() {
        return account.getOperations();
    }

    public BigDecimal getBalance() {
        return account.getBalance();
    }
}