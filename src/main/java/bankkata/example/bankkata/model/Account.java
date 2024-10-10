package bankkata.example.bankkata.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.concurrent.Semaphore;
import bankkata.example.bankkata.enums.OperationType;;

@NoArgsConstructor
@Getter
public class Account {

    private BigDecimal balance = BigDecimal.ZERO; 
    private final List<Operation> operations = new ArrayList<>(); 
    private final Semaphore semaphore = new Semaphore(1); 

    /**
     * Deposit a specified amount to the account.
     *
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if the amount is non-positive
     */
    public void deposit(BigDecimal amount) {
        validateDepositAmount(amount); 
        try {
            semaphore.acquire(); 
            balance = balance.add(amount); 
            addOperation(OperationType.DEPOSIT, amount); 
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
     * @throws IllegalArgumentException if the amount is non-positive or exceeds the balance
     */
    public void withdraw(BigDecimal amount) {
        validateWithdrawalAmount(amount); 
        try {
            semaphore.acquire(); 
            balance = balance.subtract(amount); 
            addOperation(OperationType.WITHDRAW, amount); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            throw new RuntimeException("Thread was interrupted during withdrawal operation", e);
        } finally {
            semaphore.release(); 
        }
    }

    /**
     * Retrieve the list of operations performed on the account.
     *
     * @return a list of operations
     */
    public List<Operation> getOperations() {
        return new ArrayList<>(operations); // Return a copy for safety
    }

    /**
     * Retrieve the current balance of the account.
     *
     * @return the current balance
     */
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

 
    private void addOperation(OperationType type, BigDecimal amount) {
        operations.add(new Operation(LocalDate.now(), type.name(), amount, balance)); 
    }
}
