package bank.ui.swing;

import java.awt.Point;
import bank.service.BankService;

public class GUIForm {

	public static BankService bankService = null;

	public static Login login;
	public static Menu menu;
	public static AddAccount addaccount;
	public static AddCurrentAccount addcurrentacc;
	public static AddSavingsAccount addsavingsaccount;
	public static AddStudentAccount addstudentaccount;
	public static DisplayList displaylist;
	public static DepositAcc depositacc;
	public static WithdrawAcc withdraw;

	// ✅ Call this ONCE from Application.java with BankService instance
	public static void init(BankService service) {
		bankService = service;

		// initialize all screens with injected service
		login = new Login(bankService);
		menu = new Menu(bankService);
		addaccount = new AddAccount(bankService);
		addcurrentacc = new AddCurrentAccount(bankService);
		addsavingsaccount = new AddSavingsAccount(bankService);
		addstudentaccount = new AddStudentAccount(bankService);
		displaylist = new DisplayList(bankService);
		depositacc = new DepositAcc(bankService);
		withdraw = new WithdrawAcc(bankService);
	}

	public static void UpdateDisplay() {
		if (displaylist.isVisible()) {
			Point location = displaylist.getLocation();
			displaylist.dispose();
			displaylist = new DisplayList(bankService);
			displaylist.setVisible(true);
			displaylist.setLocation(location);
		} else {
			displaylist = new DisplayList(bankService);
			displaylist.setVisible(true);
		}
	}
}
