package bank.ui.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bank.exceptions.AccNotFound;
import bank.exceptions.InvalidAmount;
import bank.exceptions.MaxBalance;
import bank.exceptions.MaxWithdraw;
import bank.service.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class WithdrawAcc extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final BankService bankService;

	public WithdrawAcc(BankService bankService) {
		this.bankService = bankService;

		setTitle("Withdraw From Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWithdrawFromAccount = new JLabel("Withdraw From Account");
		lblWithdrawFromAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWithdrawFromAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdrawFromAccount.setBounds(10, 11, 414, 36);
		contentPane.add(lblWithdrawFromAccount);

		JLabel lblName = new JLabel("Account Number:");
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

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
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
						bankService.withdraw(accountNum, amt);
						JOptionPane.showMessageDialog(getComponent(0), "Withdraw Successful");
						//dispose();
					} catch (InvalidAmount e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Invalid Amount");
					} catch (AccNotFound e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Account Not Found");
					} catch (MaxBalance e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Insufficient Balance");
					} catch (MaxWithdraw e1) {
						JOptionPane.showMessageDialog(getComponent(0), "Maximum Withdraw Limit Exceeded");
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(getComponent(0), "Unexpected Error Occurred");
					}
				}

				textField.setText(null);
				textField_1.setText(null);
			}
		});
		btnWithdraw.setBounds(73, 212, 89, 23);
		contentPane.add(btnWithdraw);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(243, 212, 89, 23);
		contentPane.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 230, 80, 25);  // Adjust position as needed
		btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
		contentPane.add(btnBack);
	}
}
