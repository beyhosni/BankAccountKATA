package bankkata.example.bankkata.controllers;

import java.math.BigDecimal;
import bankkata.example.bankkata.model.*;
import bankkata.example.bankkata.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    private final AccountService accountService;

    public BankAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam BigDecimal amount) {
        accountService.deposit(amount);
        return ResponseEntity.ok("sucessful deposit operation");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam BigDecimal amount) {
        try {
            accountService.withdraw(amount);
            return ResponseEntity.ok("sucessful withdrawal operation");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("operation failed");
        }
    }

    @GetMapping("/statement")
    public List<Operation> getStatement() {
        return accountService.getStatement();
    }


    @GetMapping("/balance")
    public BigDecimal getBalance(){
        return accountService.getBalance();
    }




}