package bank.transactions;

import bank.service.BankService;

public class DepositCommand implements TransactionCommand {
    private BankService service;
    private String name;
    private double amount;

    public DepositCommand(BankService service, String name, double amount) {
        this.service = service;
        this.name = name;
        this.amount = amount;
    }

    public void execute() throws Exception {
        service.deposit(name, amount);
    }
}