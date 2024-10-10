package bankkata.example.bankkata.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.math.BigDecimal;
import bankkata.example.bankkata.enums.OperationType;;



@Data
@AllArgsConstructor
public class Operation {
    private LocalDate date;
    private OperationType type;
    private BigDecimal amount;
    private BigDecimal balanceAfterOperation;

    /** 
     * Constructor for creating an Operation instance.
     * @param date the date of the operation
     * @param type the type of the operation (DEPOSIT or WITHDRAW)
     * @param amount the amount of money for the operation
     * @param balanceAfterOperation the balance after the operation
     */

    public Operation(LocalDate date, OperationType type, BigDecimal amount, BigDecimal balanceAfterOperation) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balanceAfterOperation = balanceAfterOperation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

