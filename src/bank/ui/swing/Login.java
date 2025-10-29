package bank.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bank.service.BankService;

public class Login {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;
	private final BankService bankService;

	public Login(BankService bankService) {
		this.bankService = bankService;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Banking System - Login");
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("Banking System");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(147, 11, 151, 41);
		frame.getContentPane().add(label);

		JLabel lblLoginScreen = new JLabel("Login Screen");
		lblLoginScreen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoginScreen.setBounds(170, 63, 101, 23);
		frame.getContentPane().add(lblLoginScreen);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(55, 119, 64, 23);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(55, 159, 64, 23);
		frame.getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setBounds(130, 121, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JPasswordField();
		textField_1.setBounds(130, 161, 150, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 200, 100, 25);
		frame.getContentPane().add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText().trim();
				String password = textField_1.getText().trim();

				if (bankService.login(username, password)) {
					JOptionPane.showMessageDialog(frame, "Login Successful!");
					frame.setVisible(false);
					GUIForm.menu.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Login Failed. Invalid username or password.");
				}
			}
		});
	}
}
