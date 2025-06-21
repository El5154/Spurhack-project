import java.time.LocalDate;

public class Transaction {
    private String name;
    private double amount;
    private LocalDate date;
    private Category category;

    public enum Category {
        INCOME,
        EXPENSE
    }  

    public Transaction(String name, double amount, LocalDate date, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    // setters used for when users want to make changes to a specific transaction
    public void setName() {

    }
    public void setAmount() {

    }
    public void setDate() {
        
    }
    public void setCategory() {

    }


    // getters for sorting or viewing history
    public String getName() {
        return this.name;
    }
    public double getAmount() {
        return this.amount;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public Category getCategory() {
        return this.category;
    }
}


