package bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountName;
    private String type; // "DEPOSIT", "WITHDRAW", "CREATE"
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String accountName, String type, double amount) {
        this.accountName = accountName;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountName() { return accountName; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp.format(java.time.format.DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a")) + "] "
                + type + " ₹" + amount + " -> " + accountName;
    }
}
