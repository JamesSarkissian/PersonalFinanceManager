

public class Transaction {
    private int transactionID;
    private int userID;
    private double amount;
    private String description;
    private String category;


    public Transaction(int transactionID, int userID, double amount, String description, String category) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public int getTransactionID() {
        return this.transactionID;
    }

    public int getUserID() {
        return this.userID;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.transactionID + " | " + description 
            + " | $" + String.format("%.2f", this.amount) + " | " + this.category;
    }
}