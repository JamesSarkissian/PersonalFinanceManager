

public class Transaction {
    private String id;
    private double amount;
    private String description;
    private String category;

    public Transaction(String id, double amount, String description, String category) {
        this.id = id;
        this.amount = amount;
        this.descripton = description;
        this.category = category;
    }

    public String getID() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}