package BasicAndOop.proggrams.ExceptionHandling;


class InsufficientFundsException extends RuntimeException {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Short by: " + amount);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }
}

public class unchecked {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        try {
            account.withdraw(800);   // valid
            account.withdraw(500);   // will throw exception
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Shortage: " + e.getAmount());
        }

        System.out.println("Remaining Balance: " + account.getBalance());
    }
}