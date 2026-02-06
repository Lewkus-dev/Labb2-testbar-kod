package se.iths.lukas.labb2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountComponent {
    AccountComponent accComp;

    @BeforeEach
    public void instantiateAccComp() {
        this.accComp = new AccountComponent();
    }

    @Test
    public void testBalanceIsZero() {
        assertEquals(0, accComp.getBalance());
    }

    @Test
    public void testDepositAndWithdraw() {
        int amount1 = 5;
        int amount2 = 3;
        accComp.deposit(amount1);
        assertEquals(amount1, accComp.getBalance());
        accComp.withdraw(amount2);
        int balance = accComp.getBalance();
        assertEquals(2, balance);
    }


}
