package bankkata.example.bankkata.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.concurrent.Semaphore;

@NoArgsConstructor
@Getter
public class Account {

    private BigDecimal balance = BigDecimal.ZERO;
    private List<Operation> operations = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);


    /**
     * Deposit a specified amount to the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(BigDecimal amount) {
        validateDepositAmount(amount); 
        try {
            semaphore.acquire(); 
            balance = balance.add(amount); 
            operations.add(new Operation(LocalDate.now(), "DEPOSIT", amount, balance));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            throw new RuntimeException("Thread was interrupted during deposit operation", e); 
        } finally {
            semaphore.release();
        }
    }

    /**
     * Withdraw a specified amount from the account.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(BigDecimal amount) {
        validateWithdrawalAmount(amount); 
        try {
            semaphore.acquire(); 
            balance = balance.subtract(amount); 
            operations.add(new Operation(LocalDate.now(), "WITHDRAW", amount, balance)); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            throw new RuntimeException("Thread was interrupted during withdrawal operation", e);
        } finally {
            semaphore.release();
        }
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void validateDepositAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive"); 
        }
    }

    private void validateWithdrawalAmount(BigDecimal amount) {
        if (!isValidAmount(amount)) {
            throw new IllegalArgumentException("Withdrawal amount must be positive"); 
        } else if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance"); 
        }
    }
    private boolean isValidAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0; 
    }
}