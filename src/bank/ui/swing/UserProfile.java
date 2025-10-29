package bank.ui.swing;

import bank.domain.User;
import bank.service.BankService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserProfile extends JFrame {

    private static final long serialVersionUID = 1L;

    public UserProfile(BankService bankService) {
        User user = bankService.getCurrentUser();

        setTitle("User Profile");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        contentPane.add(new JLabel("Full Name: " + user.getFullName()));
        contentPane.add(new JLabel("Username: " + user.getUsername()));


        JButton btnChangePass = new JButton("Change Password");
        btnChangePass.addActionListener((ActionEvent e) -> {
            String newPass = JOptionPane.showInputDialog(this, "Enter new password:");
            if (newPass != null && !newPass.trim().isEmpty()) {
                bankService.changePassword(newPass);
                JOptionPane.showMessageDialog(this, "Password updated!");
            }
        });
        contentPane.add(btnChangePass);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 230, 80, 25);  // Adjust position as needed
        btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
        contentPane.add(btnBack);
    }
}
