package se.iths.lukas.labb2;

import org.springframework.stereotype.Service;
import se.iths.lukas.labb2.exception.InsufficientFundsException;
import se.iths.lukas.labb2.exception.InvalidAmountException;
import se.iths.lukas.labb2.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {

    private AccountComponent accountComponent;

    private final int MAX_WITHDRAW_AMOUNT = 500;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void makeDeposit(int amount) throws Exception {
        if (amount == 0) {
            throw new InvalidAmountException("Deposit of 0 is not allowed.");
        } else if (amount < 0) {
            throw new InvalidAmountException("Deposit of negative value is not possible, try withdrawal.");
        } else {
            accountComponent.deposit(amount);
        }
    }

    public void makeWithdrawal(int amount) throws Exception {
        if (amount < 0) {
            throw new InvalidAmountException("You cannot withdraw " + amount + " from your account, try deposit.");
        } else if (amount == 0) {
            throw new InvalidAmountException("You cannot withdraw 0 from you account, try view balance.");
        } else if (amount > getAccountBalance()) {
            throw new InsufficientFundsException("You do not have enough balance to withdraw "
                    + amount + " from account.");
        } else if (amount > MAX_WITHDRAW_AMOUNT) {
            throw new MaxWithdrawalExceededException("You do not have permission to withdraw more than "
                    + MAX_WITHDRAW_AMOUNT + " at a time.");
        } else {
            accountComponent.withdraw(amount);
        }

    }

    public int getAccountBalance() {
        return accountComponent.getBalance();
    }
}
