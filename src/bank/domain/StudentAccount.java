package bank.domain;

import bank.exceptions.InvalidAmount;
import bank.exceptions.MaxBalance;

import java.time.LocalDate;

public class StudentAccount extends SavingsAccount {

	private static final long serialVersionUID = 1L;

	private final String institutionName;
	private final double interestRate = 0.03; // 3%
	private LocalDate lastInterestCredited = LocalDate.now();

	public StudentAccount(String name, double balance, String institutionName) throws Exception {
		// 🎁 Add ₹500 welcome bonus, and use 20000 as maxWithdrawLimit
		super(name, balance + 500, 20000);
		this.institutionName = institutionName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	@Override
	public void deposit(double amount) throws InvalidAmount, MaxBalance {
		if (amount <= 0) {
			throw new InvalidAmount("Deposit must be positive.");
		}
		if (getbalance() + amount > 50000) {
			throw new MaxBalance("Student Account cannot exceed ₹50,000.");
		}
		super.deposit(amount);
	}

	// ✅ Interest only if balance > ₹10k
	public boolean applyInterestIfDue() {
		LocalDate now = LocalDate.now();
		if (getbalance() <= 10000) return false;

		if (lastInterestCredited == null || lastInterestCredited.plusDays(30).isBefore(now)) {
			double interest = getbalance() * interestRate;
			try {
				deposit(interest);
				lastInterestCredited = now;
				return true;
			} catch (Exception e) {
				System.err.println("Interest failed: " + e.getMessage());
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + " [Institution: " + institutionName + "]";
	}
}
