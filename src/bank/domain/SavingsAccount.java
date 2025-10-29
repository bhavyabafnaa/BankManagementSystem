package bank.domain;
import bank.domain.interest.*;
import bank.exceptions.MaxBalance;
import bank.exceptions.MaxWithdraw;
import java.time.LocalDate;
import java.time.Month;


public class SavingsAccount extends BankAccount {
	private static final long serialVersionUID = 1L;
	private int monthlyWithdrawCount = 0;
	private Month lastWithdrawMonth = LocalDate.now().getMonth();


	private double calculateInterestRate() {
		double balance = getbalance();
		if (balance <= 50000) return 0.04;
		else return 0.055;
	}

	private double maxWithLimit;
	private LocalDate lastInterestCredited;

	public SavingsAccount(String name, double balance, double maxWithLimit) throws Exception {
		super(name, balance, 2000);
		this.maxWithLimit = maxWithLimit;
		this.lastInterestCredited = LocalDate.now();  // initial creation time
	}

	// 🧠 Call this monthly or during login
	public boolean applyInterestIfDue() {
		LocalDate now = LocalDate.now();
		if (lastInterestCredited.plusDays(30).isBefore(now)) {
			double rate = calculateInterestRate();
			double interest = getbalance() * rate;
			try {
				deposit(interest);
				lastInterestCredited = now;
				return true;
			} catch (Exception e) {
				System.err.println("Interest application failed: " + e.getMessage());
			}
		}
		return false;
	}


	public double getNetBalance() {
		return getbalance() + (getbalance() * calculateInterestRate());
	}


	@Override
	public void withdraw(double amount) throws MaxWithdraw, MaxBalance {
		LocalDate now = LocalDate.now();
		Month currentMonth = now.getMonth();

		// 🔁 Reset counter if new month
		if (!currentMonth.equals(lastWithdrawMonth)) {
			lastWithdrawMonth = currentMonth;
			monthlyWithdrawCount = 0;
		}

		// 🔒 Enforce monthly withdrawal limit
		if (monthlyWithdrawCount >= 3) {
			throw new MaxWithdraw("Monthly withdrawal limit exceeded (max 3 per month).");
		}

		if (amount > maxWithLimit) {
			throw new MaxWithdraw("Withdraw amount exceeds maximum limit.");
		}

		super.withdraw(amount);  // Proceed with actual withdrawal
		monthlyWithdrawCount++;
	}

}

