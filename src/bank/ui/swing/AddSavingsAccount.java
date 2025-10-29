package bank.ui.swing;
import bank.domain.interest.*;

import bank.domain.SavingsAccount;
import bank.exceptions.InvalidAmount;
import bank.service.BankService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSavingsAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldBalance;
	private JTextField textFieldMinBalance;
	private final BankService bankService;

	public AddSavingsAccount(BankService bankService) {
		this.bankService = bankService;

		setTitle("Add Savings Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add Savings Account");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(100, 10, 250, 30);
		contentPane.add(lblTitle);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(50, 70, 100, 20);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(160, 70, 200, 25);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblBalance = new JLabel("Initial Balance:");
		lblBalance.setBounds(50, 120, 100, 20);
		contentPane.add(lblBalance);

		textFieldBalance = new JTextField();
		textFieldBalance.setBounds(160, 120, 200, 25);
		contentPane.add(textFieldBalance);
		textFieldBalance.setColumns(10);

		JLabel lblMinBalance = new JLabel("Min Balance:");
		lblMinBalance.setBounds(50, 170, 100, 20);
		contentPane.add(lblMinBalance);

		textFieldMinBalance = new JTextField();
		textFieldMinBalance.setBounds(160, 170, 200, 25);
		contentPane.add(textFieldMinBalance);
		textFieldMinBalance.setColumns(10);

		JButton btnAdd = new JButton("Create Account");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textFieldName.getText();
					double balance = Double.parseDouble(textFieldBalance.getText());
					double minBalance = Double.parseDouble(textFieldMinBalance.getText());

					SavingsAccount account = new SavingsAccount(name, balance, minBalance);
					bankService.openAccount(account);

					JOptionPane.showMessageDialog(getComponent(0), "Savings Account Created Successfully!");
					//dispose();
				} catch (InvalidAmount e1) {
					JOptionPane.showMessageDialog(getComponent(0), "Invalid Amount!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getComponent(0), "Account creation failed.");
				}
			}
		});
		btnAdd.setBounds(150, 230, 150, 30);
		contentPane.add(btnAdd);
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 230, 80, 25);  // Adjust position as needed
		btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
		contentPane.add(btnBack);
	}
}
