import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction implements Comparable<Transaction> {
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
    public void setName(String name) {
        
    }
    public void setAmount(double amount) {

    }
    public void setDate(LocalDate name) {
        
    }
    public void setCategory(Category category) {

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


    


    @Override
    public int compareTo(Transaction o) {
        if (this.date.compareTo(o.date) != 0) {
            return this.date.compareTo(o.date);
        }
        else if (this.name.compareTo(o.name) != 0) {
            return this.name.compareTo(o.name);
        }
        else if (this.category.compareTo(o.category) != 0) {
            return this.category.compareTo(o.category);
        }
        else {
            return Double.compare(this.amount, o.amount);
        }
    }
}


