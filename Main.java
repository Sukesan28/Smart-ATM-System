package com;

import java.sql.*;
import java.util.Scanner;

public class Main {

    // Generate a random 12-digit account number
    public static long generateAccountNumber() {
        long base = 100000000000L;
        long random = (long) (Math.random() * 900000000000L);
        return base + random;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMService service = new ATMService();

        System.out.println("===== Welcome to Smart ATM =====");

        while (true) {
            System.out.println("\n======= Menu =======");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Mini Statement");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();
                        System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
                        String dob = sc.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phone = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();
                        System.out.print("Set a 4-digit PIN: ");
                        String pin = sc.nextLine();
                        long accountNumber = generateAccountNumber();
                        service.createAccount(firstName, lastName, dob, pin, email, phone, address, accountNumber);

                        System.out.println("💳 Your Account Number is: " + accountNumber);
                        System.out.println("💬 Please save your account number and PIN.");
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        long accNum2 = sc.nextLong();
                        double balance = service.getBalance(accNum2);
                        System.out.println("💰 Current Balance: ₹" + balance);
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        long accNum3 = sc.nextLong();
                        System.out.print("Enter deposit amount: ₹");
                        double dep = sc.nextDouble();
                        service.deposit(accNum3, dep);
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        long accNum4 = sc.nextLong();
                        System.out.print("Enter withdraw amount: ₹");
                        double amt = sc.nextDouble();
                        service.withdraw(accNum4, amt);
                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        long accNum5 = sc.nextLong();
                        service.miniStatement(accNum5);
                        break;

                    case 6:
                        System.out.println("🙏 Thank you for using Smart ATM. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("❌ Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}
