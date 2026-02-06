package se.iths.lukas.labb2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.lukas.labb2.exception.InsufficientFundsException;
import se.iths.lukas.labb2.exception.InvalidAmountException;
import se.iths.lukas.labb2.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestATMService {
    @Mock
    private AccountComponent accountComponent;

    @InjectMocks
    private ATMService atmService;

    @Test//test withdraw with < 0
    public void testWithdrawWithNegativeAmount() {
        int amount = -63;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.makeWithdrawal(amount);
        });
    }

    @Test //test withdraw with 0
    public void testWithdrawWithZero() {
        int amount = 0;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.makeWithdrawal(amount);
        });
    }

    @Test //test withdraw with over max limit
    public void testWithdrawAboveLimit() {
        when(accountComponent.getBalance()).thenReturn(3000);
        int amount = 700;
        assertThrows(MaxWithdrawalExceededException.class, () -> {
            atmService.makeWithdrawal(amount);
        });
    }

    @Test //test withdraw over balance
    public void testWithdrawAboveBalance() {
        int amount = 30;
        assertThrows(InsufficientFundsException.class, () -> {
            atmService.makeWithdrawal(amount);
        });
    }

    @Test // test withdraw with a valid argument
    public void testWithdraw() throws Exception {
        when(accountComponent.getBalance())
                .thenReturn(30)
                .thenReturn(27);
        int amount = 3;
        atmService.makeWithdrawal(amount);
        assertEquals(27, atmService.getAccountBalance());
    }

    @Test //test deposit with < 0
    public void testDepositWithLessThanZero() {
        int amount = -10;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.makeDeposit(amount);
        });
    }

    @Test //test deposit with 0
    public void testDepositWithZero() {
        int amount = 0;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.makeDeposit(amount);
        });
    }

    @Test // test deposit with valid argument
    public void testDepositWithValid() throws Exception {
        atmService.makeDeposit(11);
        verify(accountComponent).deposit(11);

    }


}
