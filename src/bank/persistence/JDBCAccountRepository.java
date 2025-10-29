package bank.persistence;

import bank.domain.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountRepository implements AccountRepository {

    private final Connection conn;

    public JDBCAccountRepository() throws Exception {
        // Load H2 Driver
        Class.forName("org.h2.Driver");

        // Connect to H2 DB (stored locally as 'bankDB.mv.db')
        conn = DriverManager.getConnection("jdbc:h2:./bankDB", "sa", "");

        // Auto-create table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "holder_name VARCHAR(255), " +
                "balance DOUBLE, " +
                "type VARCHAR(50), " +
                "min_balance DOUBLE)";
        conn.createStatement().executeUpdate(sql);
    }

    @Override
    public void save(BankAccount acc) throws Exception {
        String sql = "INSERT INTO accounts(holder_name, balance, type, min_balance) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, acc.getName());
        ps.setDouble(2, acc.getbalance());
        ps.setString(3, acc.getClass().getSimpleName());
        ps.setDouble(4, acc.getMinBalance());
        ps.executeUpdate();
    }

    @Override
    public BankAccount findById(String name) throws Exception {
        String sql = "SELECT * FROM accounts WHERE holder_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new BankAccount(
                    rs.getString("holder_name"),
                    rs.getDouble("balance"),
                    rs.getDouble("min_balance")
            );
        }
        return null;
    }

    @Override
    public List<BankAccount> findAll() throws Exception {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        ResultSet rs = conn.createStatement().executeQuery(sql);

        while (rs.next()) {
            BankAccount acc = new BankAccount(
                    rs.getString("holder_name"),
                    rs.getDouble("balance"),
                    rs.getDouble("min_balance")
            );
            accounts.add(acc);
        }
        return accounts;
    }

    @Override
    public void update(BankAccount acc) throws Exception {
        String sql = "UPDATE accounts SET balance = ? WHERE holder_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, acc.getbalance());
        ps.setString(2, acc.getName());
        ps.executeUpdate();
    }

    @Override
    public BankAccount delete(String name) throws Exception {
        String sql = "DELETE FROM accounts WHERE holder_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        return null;
    }
}
