import java.time.LocalDate;
import java.util.TreeSet;


public class TransactionManager {
    private TreeSet<Transaction> allTransactions = new TreeSet<>();


    // methods for updating the transaction properties
    public void updateName(Transaction t, String name) {
        t.setName(name);
    }

    public void updateAmount(Transaction t, double amount) {
        t.setAmount(amount);
    }
    
    public void updateDate(Transaction t, LocalDate date) {
        allTransactions.remove(t);
        t.setDate(date);
        allTransactions.add(t);
    }

    public void updateCategory(Transaction t, Transaction.Category category) {
        t.setCategory(category);
    }

}

