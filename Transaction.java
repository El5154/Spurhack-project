import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {
    private String name;
    private double amount;
    private LocalDate date;
    private Category category;

    public enum Category {
        REVENUE,
        EXPENSE
    }  

    public Transaction(String name, double amount, LocalDate date, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    // setters used for when users want to make changes to a specific transaction
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setCategory(Category category) {
        this.category = category;
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

    public int getMonth() {
        return this.date.getMonthValue();
    }

    public int getYear() {
        return this.date.getYear();
    }
    
    public Category getCategory() {
        return this.category;
    }


    public String toString() {
        return "Transaction{ " +
                "name = '" + name + '\'' + "amount = " + amount +
                ", date = " + date + "category = " + category + " }";
    }


    @Override
    public int compareTo(Transaction o) {
        if (this.date.compareTo(o.date) != 0) {
            return this.date.compareTo(o.date);
        }
        else if (this.name.toUpperCase().compareTo(o.name.toUpperCase()) != 0) {
            return this.name.toUpperCase().compareTo(o.name.toUpperCase());
        }
        else if (this.category.compareTo(o.category) != 0) {
            return this.category.compareTo(o.category);
        }
        else {
            return Double.compare(this.amount, o.amount);
        }
    }
}


