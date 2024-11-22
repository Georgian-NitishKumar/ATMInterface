import java.util.Scanner;

// Class to represent user's bank account
class BankAccount {
    private double balance;
    private final String accountNumber;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Class to represent ATM machine
class ATM {
    private final BankAccount userAccount;
    private final Scanner scanner;
    
    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Please select an option: ");
    }
    
    public void checkBalance() {
        System.out.printf("\nCurrent balance: $%.2f%n", userAccount.getBalance());
    }
    
    public void deposit() {
        System.out.print("\nEnter amount to deposit: $");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Error: Invalid amount. Please enter a positive value.");
            return;
        }
        
        userAccount.deposit(amount);
        System.out.printf("Successfully deposited $%.2f%n", amount);
        System.out.printf("New balance: $%.2f%n", userAccount.getBalance());
    }
    
    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Error: Invalid amount. Please enter a positive value.");
            return;
        }
        
        if (amount > userAccount.getBalance()) {
            System.out.println("Error: Insufficient funds!");
            System.out.printf("Current balance: $%.2f%n", userAccount.getBalance());
            return;
        }
        
        if (userAccount.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f%n", amount);
            System.out.printf("Remaining balance: $%.2f%n", userAccount.getBalance());
        }
    }
    
    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Account: " + userAccount.getAccountNumber());
        
        while (true) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("\nThank you for using our ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                }
                
            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please try again.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
}

// Main class to run the ATM program
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with initial balance
        BankAccount account = new BankAccount("1234567890", 1000.0);
        
        // Create and start ATM
        ATM atm = new ATM(account);
        atm.start();
    }
}