package bank.transactions;

public interface TransactionCommand {
    void execute() throws Exception;
}