package bank.ui.swing;

import bank.domain.Transaction;
import bank.service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private final BankService bankService;
    private JTextArea textArea;

    private JTextField accountField;
    private JComboBox<String> typeBox;
    private JComboBox<String> dateDropdown;

    public TransactionPanel(BankService bankService) {
        this.bankService = bankService;

        setTitle("Transaction History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(SystemColor.activeCaption);
        setContentPane(contentPane);

        // 🧾 Title
        JLabel lblTitle = new JLabel("Transaction History");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // 📄 Transaction display area
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 🔍 Filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter"));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Account Name:"));
        accountField = new JTextField(10);
        row1.add(accountField);

        row1.add(new JLabel("Type:"));
        typeBox = new JComboBox<>(new String[]{"ALL", "DEPOSIT", "WITHDRAW", "CREATE"});
        row1.add(typeBox);
        filterPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("After Date:"));
        dateDropdown = new JComboBox<>(new String[]{
                "Any", "Today", "Last 3 Days", "Last 7 Days", "Last 30 Days"
        });
        row2.add(dateDropdown);

        JButton btnFilter = new JButton("Apply Filter");
        btnFilter.addActionListener(e -> displayTransactions());
        row2.add(btnFilter);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFilters());
        row2.add(btnClear);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> NavigationUtil.goBack(this, GUIForm.menu));
        row2.add(btnBack);

        filterPanel.add(row2);
        contentPane.add(filterPanel, BorderLayout.SOUTH);

        displayTransactions();  // initial load
    }

    private void clearFilters() {
        accountField.setText("");
        typeBox.setSelectedIndex(0); // ALL
        dateDropdown.setSelectedIndex(0); // Any
        displayTransactions();
    }

    private void displayTransactions() {
        try {
            List<Transaction> transactions = bankService.getAllTransactions();

            // Account filter
            String accountFilter = accountField.getText().trim();
            if (!accountFilter.isEmpty()) {
                transactions = transactions.stream()
                        .filter(t -> t.getAccountName().equalsIgnoreCase(accountFilter))
                        .collect(Collectors.toList());
            }

            // Type filter
            String selectedType = (String) typeBox.getSelectedItem();
            if (!selectedType.equals("ALL")) {
                transactions = transactions.stream()
                        .filter(t -> t.getType().equalsIgnoreCase(selectedType))
                        .collect(Collectors.toList());
            }

            // Date filter via dropdown
            String dateOption = (String) dateDropdown.getSelectedItem();
            if (!dateOption.equals("Any")) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime fromDate = switch (dateOption) {
                    case "Today" -> now.minusHours(24);
                    case "Last 3 Days" -> now.minusDays(3);
                    case "Last 7 Days" -> now.minusDays(7);
                    case "Last 30 Days" -> now.minusDays(30);
                    default -> null;
                };

                if (fromDate != null) {
                    transactions = transactions.stream()
                            .filter(t -> t.getTimestamp().isAfter(fromDate))
                            .collect(Collectors.toList());
                }
            }

            // Display results
            StringBuilder sb = new StringBuilder();
            if (transactions.isEmpty()) {
                sb.append("No transactions match the selected filters.");
            } else {
                for (Transaction t : transactions) {
                    sb.append(t.toString()).append("\n");
                }
            }
            textArea.setText(sb.toString());

        } catch (Exception e) {
            textArea.setText("Error loading transactions: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
