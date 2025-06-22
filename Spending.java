import java.util.ArrayList;
import java.util.HashMap;

public class Spending{

  private ArrayList<Transaction> spendingTransactions = new ArrayList<>();

  private HashMap<Integer, Double> spendingMap = new HashMap<>();
  {
      spendingMap.put(1, 0.0);
      spendingMap.put(2, 0.0);
      spendingMap.put(3, 0.0);
      spendingMap.put(4, 0.0);
      spendingMap.put(5, 0.0);
      spendingMap.put(6, 0.0);
      spendingMap.put(7, 0.0);
      spendingMap.put(8, 0.0);
      spendingMap.put(9, 0.0);
      spendingMap.put(10, 0.0);
      spendingMap.put(11, 0.0);
      spendingMap.put(12, 0.0);
  }
  public Spending(ArrayList<Transaction> spendingTransactions) {
      this.spendingTransactions = spendingTransactions;

  }

  public HashMap<Integer, Double> getMonthlySpending() {
      for (Transaction t : spendingTransactions) {
          spendingMap.put(t.getMonth(), spendingMap.get(t.getMonth()) + t.getAmount());
      }

      return spendingMap;
  }

  
}
