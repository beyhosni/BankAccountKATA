package bankkata.example.bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import bankkata.example.bankkata.model.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class BankkataApplicationTests {

	private Account account;
	
	@BeforeEach
	public void setUp() {
		account = new Account();
	}

	@Test
	public void testAbilityDepositInAccount() {
		BigDecimal initialBalance = account.getBalance();
		account.deposit(new BigDecimal("1000"));
		assertEquals(initialBalance.add(new BigDecimal("1000")), account.getBalance(), "The balance should increase after a deposit.");
	}

    @Test
    public void testAbilityToWithdrawFromAccount() {
        account.deposit(new BigDecimal("1000"));
        BigDecimal initialBalance = account.getBalance();
        account.withdraw(new BigDecimal("500"));
        assertEquals(initialBalance.subtract(new BigDecimal("500")), account.getBalance(), "The balance should decrease after a withdrawal.");
    }
    @Test
    public void testPrintStatementHistory() {
        account.deposit(new BigDecimal("1000"));
        account.withdraw(new BigDecimal("200"));

        List<Operation> statement = account.getOperations();

        assertEquals(2, statement.size(), "There should be 2 operations in the statement.");
        assertEquals("DEPOSIT", statement.get(0).getType(), "First operation should be a deposit.");
        assertEquals("WITHDRAW", statement.get(1).getType(), "Second operation should be a withdrawal.");
	}
	
    @Test
    public void testInsufficientBalance() {
        account.deposit(new BigDecimal("500"));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(new BigDecimal("1000"));
        });
        assertEquals("Insufficient balance.", thrown.getMessage(), "Withdrawal should throw an exception for insufficient balance.");
    }

}
