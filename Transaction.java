import java.time.LocalDate;
import java.util.ArrayList;

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
}


