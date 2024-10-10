package bankkata.example.bankkata.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Account {

    private BigDecimal balance = BigDecimal.ZERO;
    private List<Operation> operations = new ArrayList<>();


    
    public void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Error Deposit");
        }
        balance = balance.add(amount);
        operations.add(new Operation(LocalDate.now(),"DEPOSIT", amount, balance));
    }

    public void withdraw(BigDecimal amount) {
       if (amount.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Error Withdrawl");
       } else if (balance.compareTo(amount) < 0 ){
           throw new IllegalArgumentException("Insufficient balance");
       }
       balance = balance.subtract(amount);
       operations.add(new Operation(LocalDate.now(),"WITHDRAW",amount,balance));

    }

    public List<Operation> getOperations() {
        return operations;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}