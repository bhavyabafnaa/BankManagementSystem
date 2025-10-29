package bank.domain.interest;

public class SavingsInterest implements InterestStrategy {
    public double calculateInterest(double balance) {
        return balance * 0.04;
    }
}
