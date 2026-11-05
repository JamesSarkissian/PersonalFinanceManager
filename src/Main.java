import java.util.ArrayList;


public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static TransactionManager manager = new TransactionManager();

    private static int nextUserID = 1;
    private static int nextTransactionID = 1;

    public static void main(String[] args) {
        while (true) {
            IO.println("\n--- Personal Finance Manager ---");
            IO.println("1. Register");
            IO.println("2. Login");
            IO.println("3. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    IO.println("Closing Application");
                    return;
                default:
                    IO.println("Invalid choice.");
            }
        }
    }

    public static void register() {
        String username = IO.readln("Enter username: ").trim();

        if (username.isEmpty()) {
            IO.println("Username cannot be empty");
            return;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                IO.println("Username already exists.");
                return;
            }
        }

        String password = IO.readln("Enter password: ");

        if (password.isEmpty()) {
            IO.println("Password cannot be empty.");
            return;
        }

        User user = new User(nextUserID, username, password);
        users.add(user);
        nextUserID++;

        IO.println("Account created successfully!");
    }

    public static void login() {
        String username = IO.readln("Enter username: ");
        String password = IO.readln("Enter password: ");

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                IO.println("Login Successful!");
                userMenu(user);
                return;
            }
        }

        IO.println("Invalid username or password");
    }

    public static void userMenu(User user) {
        while (true) {
            IO.println("\n=== Welcome, " + user.getUsername() + " ===");
            IO.println("1. Add Transaction");
            IO.println("2. Remove Transaction");
            IO.println("3. View All Transactions");
            IO.println("4. View Expenses");
            IO.println("5. View Income");
            IO.println("6. View Balance");
            IO.println("7. Logout");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addTransaction(user);
                    break;
                case 2:
                    removeTransaction(user);
                    break;
                case 3:
                    displayTransactions(manager.getTransactions(user.getUserID()));
                    break;
                case 4:
                    displayTransactions(manager.getExpenses(user.getUserID()));
                    break;
                case 5:
                    displayTransactions(manager.getIncomes(user.getUserID()));
                    break;
                case 6:
                    IO.println("Balance: $" + String.format("%.2f",
                            manager.getBalance(user.getUserID())));
                    break;
                case 7:
                    IO.println("Logged out.");
                    return;
                default:
                    IO.println("Invalid choice.");
            }
        }
    }

    public static void addTransaction(User user) {
        String description = IO.readln("Enter description: ");

        double amount = readDouble(
                "Enter amount (positive = income, negative = expense): ");

        String category = IO.readln("Enter category: ");

        Transaction transaction = new Transaction(
                nextTransactionID,
                user.getUserID(),
                amount,
                description,
                category
        );

        manager.addTransaction(transaction);
        nextTransactionID++;

        IO.println("Transaction added successfully!");
    }

    
    public static void removeTransaction(User user) {
        ArrayList<Transaction> transactions = manager.getTransactions(user.getUserID());

        if (transactions.isEmpty()) {
            IO.println("No transactions to remove.");
            return;
        }

        displayTransactions(transactions);

        int transactionID = readInt("\nEnter transaction ID to remove: ");

        boolean removed = manager.removeTransaction(
                transactionID, user.getUserID());

        if (removed) {
            IO.println("Transaction removed successfully!");
        } else {
            IO.println("Transaction not found.");
        }
    }


    public static void displayTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            IO.println("No transactions found.");
            return;
        }

        IO.println("\n--- TRANSACTIONS ---");

        for (Transaction transaction : transactions) {
            IO.println(transaction);
        }
    }

    public static int readInt(String input) {
        while (true) {
            try {
                return Integer.parseInt(IO.readln(input).trim());
            } catch (NumberFormatException e) {
                IO.println("Please enter a valid integer.");
            }
        }
    }

    public static double readDouble(String input) {
        while (true) {
            try {
                return Double.parseDouble(IO.readln(input).trim());
            } catch (NumberFormatException e) {
                IO.println("Please enter a valid amount.");
            }
        }
    }
}