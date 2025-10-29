package bank.ui.swing;

import bank.service.BankService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final BankService bankService;

	public Menu(BankService bankService) {
		this.bankService = bankService;

		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("Bank Management Menu");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(80, 20, 280, 30);
		contentPane.add(lblTitle);

		int buttonWidth = 180;
		int buttonHeight = 30;
		int leftMargin = 130;
		int top = 70;
		int gap = 10;

		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnAddAccount.addActionListener(e -> {
			new AddAccount(bankService).setVisible(true);
			dispose();
		});
		contentPane.add(btnAddAccount);

		top += buttonHeight + gap;

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnDeposit.addActionListener(e -> {
			new DepositAcc(bankService).setVisible(true);
			dispose();
		});
		contentPane.add(btnDeposit);

		top += buttonHeight + gap;

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnWithdraw.addActionListener(e -> {
			new WithdrawAcc(bankService).setVisible(true);
			dispose();
		});
		contentPane.add(btnWithdraw);

		top += buttonHeight + gap;

		JButton btnViewAccounts = new JButton("View Accounts");
		btnViewAccounts.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnViewAccounts.addActionListener(e -> {
			new DisplayList(bankService).setVisible(true);
			dispose();
		});
		contentPane.add(btnViewAccounts);

		top += buttonHeight + gap;

		JButton btnTransactions = new JButton("View Transactions");
		btnTransactions.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnTransactions.addActionListener(e -> new TransactionPanel(bankService).setVisible(true));
		contentPane.add(btnTransactions);

		top += buttonHeight + gap;

		JButton btnProfile = new JButton("My Profile");
		btnProfile.setBounds(leftMargin, top, buttonWidth, buttonHeight);
		btnProfile.addActionListener(e -> new UserProfile(bankService).setVisible(true));
		contentPane.add(btnProfile);

		// Logout button
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 320, 100, 25);
		btnLogout.addActionListener(e -> {
			dispose();
			GUIForm.login.frame.setVisible(true); // Show login screen again
		});
		contentPane.add(btnLogout);
	}
}
