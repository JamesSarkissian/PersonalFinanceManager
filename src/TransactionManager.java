import java.util.ArrayList;

public class TransactionManager {
    private ArrayList<Transaction> transactions;

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public boolean removeTransaction(int transactionID, int userID) {
        return transactions.removeIf(transaction -> 
            transaction.getTransactionID() == transactionID &&
            transaction.getUserID() == userID);
    }

    public ArrayList<Transaction> getTransactions(int userID) {
        ArrayList<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserID() == userID) {
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }

    public double getBalance(int userID) {
        double balance = 0.0;

        for (Transaction transaction : getTransactions(userID)) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public ArrayList<Transaction> getExpenses(int userID) {
         ArrayList<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserID() == userID && transaction.getAmount() < 0.0) {
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }
    
    public ArrayList<Transaction> getIncomes(int userID) {
         ArrayList<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserID() == userID && transaction.getAmount() > 0.0) {
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }


}