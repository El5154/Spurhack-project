import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class TransactionManager {
    private ArrayList<Transaction> allTransactions = new ArrayList<>();

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
        allTransactions = new ArrayList<>();
        String filePath = "TransactionDatabase"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String name = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    LocalDate date = LocalDate.parse(parts[2].trim());
                    String categoryStr = parts[3].trim().toUpperCase();
                    Transaction.Category category = Transaction.Category.valueOf(categoryStr);

                    Transaction t = new Transaction(name, amount, date, category);
                    allTransactions.add(t);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    public void addTransaction(Transaction t) {
        allTransactions.add(t);
        saveTransactions();
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


