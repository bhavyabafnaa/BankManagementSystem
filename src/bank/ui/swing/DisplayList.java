package bank.ui.swing;

import bank.domain.BankAccount;
import bank.service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class DisplayList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private final BankService bankService;

	private JTextField deleteField;

	public DisplayList(BankService bankService) {
		this.bankService = bankService;

		setTitle("Display All Accounts");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		// Title
		JLabel lblTitle = new JLabel("All Bank Accounts");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);

		// Display Area
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Bottom Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		deleteField = new JTextField(15);
		deleteField.setToolTipText("Enter Account Name to Delete");
		bottomPanel.add(deleteField);

		JButton btnDelete = new JButton("Delete Account");
		btnDelete.addActionListener(e -> handleDelete());
		bottomPanel.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
		bottomPanel.add(btnBack);

		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		displayAccounts(); // initial load
	}

	private void displayAccounts() {
		try {
			List<BankAccount> accounts = bankService.getAllAccounts();
			StringBuilder sb = new StringBuilder();
			for (BankAccount acc : accounts) {
				sb.append(acc.toString()).append("\n");
			}
			textArea.setText(sb.toString());
		} catch (Exception e) {
			textArea.setText("Error fetching accounts: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		String name = deleteField.getText().trim();
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter an account name to delete.");
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete: " + name + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				BankAccount deleted = bankService.delete(name);
				if (deleted != null) {
					JOptionPane.showMessageDialog(this, "Deleted Account: " + deleted.toString());
				} else {
					JOptionPane.showMessageDialog(this, "No such account found.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error deleting account: " + ex.getMessage());
			}
			displayAccounts();
		}
	}
}
