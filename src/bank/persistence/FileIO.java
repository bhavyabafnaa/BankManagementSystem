package bank.persistence;

import bank.domain.BankAccount;
import bank.domain.Transaction;
import bank.service.BankService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO implements AccountRepository {

	public static BankService bankService = null;

	public static void Read() {
		FileInputStream fis = null;
		ObjectInputStream oin = null;
		try {
			fis = new FileInputStream("data");
			oin = new ObjectInputStream(fis);
			FileIO.bankService = (BankService) oin.readObject();
		} catch (Exception e) {
			try {
				FileIO.bankService = new BankService(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (oin != null) oin.close();
				if (fis != null) fis.close();
			} catch (IOException e) {
				// silently ignore
			}
		}
	}

	public static void Write() {
		try {
			FileOutputStream fout = new FileOutputStream("data");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(FileIO.bankService);
			out.flush();
			fout.close();
		} catch (Exception e) {
			// silently ignore
		}
	}

	// ✅ Implementation of AccountRepository interface

	@Override
	public void save(BankAccount acc) throws Exception {
		if (bankService == null) Read();
		bankService.openAccount(acc);
		Write();
	}

	@Override
	public BankAccount findById(String name) throws Exception {
		if (bankService == null) Read();
		return bankService.getAccount(name);
	}

	private static final String TXN_FILE = "transactions.db";
	private static List<Transaction> transactions = new ArrayList<>();

	public static void loadTransactions() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TXN_FILE))) {
			transactions = (List<Transaction>) in.readObject();
		} catch (Exception e) {
			transactions = new ArrayList<>();
		}
	}

	public static void saveTransactions() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TXN_FILE))) {
			out.writeObject(transactions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addTransaction(Transaction txn) {
		transactions.add(txn);
		saveTransactions();
	}

	public static List<Transaction> getTransactions() {
		return transactions;
	}


	@Override
	public void update(BankAccount acc) throws Exception {
		if (bankService == null) Read();
		// For simplicity, we just re-save the account
		save(acc);
	}

	@Override
	public BankAccount delete(String name) throws Exception {
		if (bankService == null) Read();

		BankAccount acc = bankService.getAccount(name);
		if (acc == null) throw new Exception("Account not found");

		bankService.getAllAccounts().remove(acc);
		Write();
		return acc;
	}



	@Override
	public List<BankAccount> findAll() throws Exception {
		if (bankService == null) Read();
		return bankService.getAllAccounts();
	}
}
