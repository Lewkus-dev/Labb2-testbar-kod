package se.iths.lukas.labb2;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private int balance = 0;

    //metod för insätt
    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    //metod för uttag
    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

    public int getBalance() {
        return this.balance;
    }
}
