package bank.ui.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bank.service.BankService;

public class AddAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final BankService bankService;

	public AddAccount(BankService bankService) {
		this.bankService = bankService;

		setTitle("Add Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddAccount = new JLabel("Add Account");
		lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAccount.setBounds(108, 11, 210, 34);
		contentPane.add(lblAddAccount);

		// ➕ Add Savings Account
		JButton btnAddSavingsAccount = new JButton("Add Saving Account");
		btnAddSavingsAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddSavingsAccount(bankService).setVisible(true);
				//dispose();
			}
		});
		btnAddSavingsAccount.setBounds(118, 56, 193, 38);
		contentPane.add(btnAddSavingsAccount);

		// ➕ Add Current Account
		JButton btnAddCurrentAccount = new JButton("Add Current Account");
		btnAddCurrentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCurrentAccount(bankService).setVisible(true);
				//dispose();
			}
		});
		btnAddCurrentAccount.setBounds(118, 124, 193, 38);
		contentPane.add(btnAddCurrentAccount);

		// ➕ Add Student Account
		JButton btnAddStudentAccount = new JButton("Add Student Account");
		btnAddStudentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddStudentAccount(bankService).setVisible(true);
				//dispose();
			}
		});
		btnAddStudentAccount.setBounds(118, 190, 193, 38);
		contentPane.add(btnAddStudentAccount);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 230, 80, 25);  // Adjust position as needed
		btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
		contentPane.add(btnBack);


	}
}
