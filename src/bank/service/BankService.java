package bank.service;

import bank.domain.*;
import bank.exceptions.AccNotFound;
import bank.persistence.AccountRepository;
import bank.persistence.FileIO;
import bank.persistence.JDBCAccountRepository;

import java.util.ArrayList;
import java.util.List;

public class BankService {

	private final AccountRepository repo;
	private List<Transaction> transactions = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private User currentUser = null;

	public List<Transaction> getAllTransactions() {
		return FileIO.getTransactions();
	}

	public void logTransaction(Transaction txn) {
		FileIO.addTransaction(txn);
	}


	public void seedUsers() {
		users.add(new User("admin", "admin", "Administrator"));
		users.add(new User("john", "1234", "John Doe"));
	}

	public boolean login(String username, String password) {
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				currentUser = u;
				return true;
			}
		}
		return false;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void changePassword(String newPass) {
		if (currentUser != null) currentUser.setPassword(newPass);
	}

	// constructor: choose between FileIO and JDBC
	public BankService(boolean useFileIO) throws Exception {
		if (useFileIO) {
			this.repo = new FileIO(); // existing file-based system
		} else {
			this.repo = new JDBCAccountRepository(); // new H2 database
		}
	}

	// Example methods using the repository
	public void openAccount(BankAccount acc) throws Exception {
		repo.save(acc);
		logTransaction(new Transaction(acc.getName(), "CREATE", acc.getbalance()));
	}

	public BankAccount getAccount(String name) throws Exception {
		BankAccount acc = repo.findById(name);
		if (acc == null) throw new AccNotFound("Account not found");

		// ✅ Apply interest if it's a SavingsAccount
		if (acc instanceof SavingsAccount sa) {
			boolean applied = sa.applyInterestIfDue();
			if (applied) {
				repo.update(sa); // Save updated balance
			}
		}
		if (acc instanceof StudentAccount sa) {
			sa.applyInterestIfDue();
		}

		return acc;
	}


	public void withdraw(String name, double amount) throws Exception {
		BankAccount acc = repo.findById(name);
		if (acc == null) throw new AccNotFound("Account not found");
		acc.withdraw(amount);
		repo.update(acc);
		logTransaction(new Transaction(name, "WITHDRAW", amount));
	}

	public List<BankAccount> getAllAccounts() throws Exception {
		return repo.findAll();
	}

	public void updateAccount(BankAccount acc) throws Exception {
		repo.update(acc);
	}

	public BankAccount delete(String name) throws Exception {
		BankAccount acc = repo.findById(name);
		if (acc == null) throw new Exception("Account not found");
		repo.delete(name);
		return acc;
	}

	public void deposit(String name, double amount) throws Exception {
		BankAccount acc = repo.findById(name);
		if (acc == null) throw new Exception("Account not found");
		acc.deposit(amount);
		repo.update(acc);
		logTransaction(new Transaction(name, "DEPOSIT", amount));
	}


}
