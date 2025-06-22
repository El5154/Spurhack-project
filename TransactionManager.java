import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class TransactionManager {
    private ArrayList<Transaction> allTransactions = new ArrayList<>();
    private ArrayList<Transaction> spendingTransactions = new ArrayList<>();

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public ArrayList<Transaction> getSpendingTransactions() {
        for (Transaction t : allTransactions) {
            if (t.getCategory() == Transaction.Category.EXPENSE && LocalDate.now().getYear() == t.getYear()) {
                spendingTransactions.add(t);
            }   
        }
        
        return spendingTransactions;
    }

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
        String filePath = "TransactionDatabase.txt"; 

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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    public void addTransaction(Transaction t) {
        allTransactions.add(t);
    }

    public void removeTransaction(Transaction t) {
        allTransactions.remove(t);
    }

    public void saveTransactions() {
        Collections.sort(allTransactions);
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


