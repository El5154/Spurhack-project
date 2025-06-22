import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;


public class TransactionManager {
    private ArrayList<Transaction> allTransactions = new ArrayList<>();

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }
    // methods for updating the transaction properties
    public void updateName(Transaction t, String name) {
        t.setName(name);
    }

    public void updateAmount(Transaction t, double amount) {
        t.setAmount(amount);
    }
    
    public void updateDate(Transaction t, LocalDate date) {
        t.setDate(date);
    }

    public void updateCategory(Transaction t, Transaction.Category category) {
        t.setCategory(category);
    }

    public void loadTransActions() {
        
    }

    public void addTransaction(Transaction t) {
        allTransactions.add(t);
    }

    public void removeTransaction(Transaction t) {
        allTransactions.remove(t);
    }

    public void saveTransactions() {
        allTransactions.sort(Transaction::compareTo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TransactionDatabase.txt"))) {
            for (Transaction t : allTransactions) {
                writer.write(t.getName() + "," + t.getAmount() + "," + t.getDate() + "," + t.getCategory());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

