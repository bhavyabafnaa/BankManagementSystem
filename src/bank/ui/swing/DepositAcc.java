package bank.ui.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bank.exceptions.AccNotFound;
import bank.exceptions.InvalidAmount;
import bank.service.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class DepositAcc extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private final BankService bankService;

	public DepositAcc(BankService bankService) {
		this.bankService = bankService;

		setTitle("Deposit To Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDepositToAccount = new JLabel("Deposit To Account");
		lblDepositToAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDepositToAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepositToAccount.setBounds(10, 11, 414, 36);
		contentPane.add(lblDepositToAccount);

		JLabel lblName = new JLabel("Account Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(0, 86, 111, 14);
		contentPane.add(lblName);

		textField = new JTextField();
		textField.setBounds(121, 83, 211, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(121, 147, 211, 20);
		contentPane.add(textField_1);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(0, 150, 111, 14);
		contentPane.add(lblAmount);

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(73, 212, 89, 23);
		contentPane.add(btnDeposit);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(243, 212, 89, 23);
		contentPane.add(btnReset);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 260, 80, 23);
		btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
		contentPane.add(btnBack);

		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountNum = textField.getText();
				double amt;

				try {
					amt = Double.parseDouble(textField_1.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(getComponent(0), "Amount must be a number.");
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(getComponent(0), "Confirm?");
				if (confirm == 0) {
					try {
						bankService.deposit(accountNum, amt);
						JOptionPane.showMessageDialog(getComponent(0), "Deposit Successful");
						//dispose();
					} catch (InvalidAmount e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Sorry! Deposit Amount is Invalid");
					} catch (AccNotFound e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Sorry! Account is Not Found");
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(getComponent(0), "An unexpected error occurred.");
					}
				}

				textField.setText(null);
				textField_1.setText(null);
			}
		});

		btnReset.addActionListener(e -> {
			textField.setText(null);
			textField_1.setText(null);
		});
	}
}
