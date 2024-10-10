package bankkata.example.bankkata.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Operation {
    private LocalDate date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfterOperation;

    public Operation(LocalDate date, String type, BigDecimal amount, BigDecimal balanceAfterOperation) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

