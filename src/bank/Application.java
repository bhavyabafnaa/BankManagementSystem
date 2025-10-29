package bank;

import java.awt.EventQueue;

import bank.domain.BankAccount;
import bank.persistence.FileIO;
import bank.service.BankService;
import bank.ui.swing.GUIForm;

public class Application {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ✅ Step 1: Initialize BankService with FileIO (true) or JDBC (false)
					BankService bankService = new BankService(false);

					// ✅ Step 2: Seed default users into memory
					bankService.seedUsers();  // Adds "admin" and "john"

					// ✅ Step 4: Initialize the GUI system with bankService dependency
					GUIForm.init(bankService);

					// ✅ Step 5: Show login window
					GUIForm.login.frame.setVisible(true);
					FileIO.Read();
					FileIO.loadTransactions();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
