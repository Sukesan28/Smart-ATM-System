package com;

import com.DBConnection;
import java.sql.*;

public class ATMService {

    public void createAccount(String firstName, String lastName, String dob, String pin, String email, String phone, String address, long accountNumber) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO accounts (first_name, last_name, date_of_birth, pin, email, phone, address, account_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, dob);
            pst.setString(4, pin);
            pst.setString(5, email);
            pst.setString(6, phone);
            pst.setString(7, address);
            pst.setLong(8, accountNumber);
            pst.executeUpdate();
            System.out.println("✅ Account created successfully.");
        }
    }

    public double getBalance(long accountNumber) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
            pst.setLong(1, accountNumber);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                System.out.println("❌ Account not found.");
            }
        }
        return 0.0;
    }

    public void deposit(long accountNumber, double amount) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement update = con.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE account_number = ?");
            update.setDouble(1, amount);
            update.setLong(2, accountNumber);
            update.executeUpdate();

            PreparedStatement insert = con.prepareStatement("INSERT INTO transactions (account_number, type, amount) VALUES (?, 'deposit', ?)");
            insert.setLong(1, accountNumber);
            insert.setDouble(2, amount);
            insert.executeUpdate();

            con.commit();
            System.out.println("✅ Deposit successful.");
        }
    }

    public boolean withdraw(long accountNumber, double amount) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            double currentBalance = getBalance(accountNumber);
            if (amount > currentBalance) {
                System.out.println("❌ Insufficient balance.");
                return false;
            }

            PreparedStatement update = con.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE account_number = ?");
            update.setDouble(1, amount);
            update.setLong(2, accountNumber);
            update.executeUpdate();

            PreparedStatement insert = con.prepareStatement("INSERT INTO transactions (account_number, type, amount) VALUES (?, 'withdraw', ?)");
            insert.setLong(1, accountNumber);
            insert.setDouble(2, amount);
            insert.executeUpdate();

            con.commit();
            System.out.println("✅ Withdrawal successful.");
            return true;
        }
    }

    public void miniStatement(long accountNumber) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement(
                    "SELECT type, amount, transaction_time FROM transactions WHERE account_number = ? ORDER BY transaction_time DESC LIMIT 5"
            );
            pst.setLong(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            System.out.println("📋 Mini Statement (Last 5 Transactions):");
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                Timestamp time = rs.getTimestamp("transaction_time");
                System.out.printf("%-10s ₹%.2f at %s\n", type, amount, time);
            }
        }
    }
}
