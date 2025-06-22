import java.util.ArrayList;
import java.util.HashMap;

public class Spending{

  private ArrayList<Transaction> spendingTransactions = new ArrayList<>();
  private double totalSpending;
  private int hasMonth;

  public Spending() {
      
  }

  public HashMap<Integer, Double> getMonthlySpending(ArrayList<Transaction> spendingTransactions) {
    HashMap<Integer, Double> spendingMap = new HashMap<>();
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
      for (Transaction t : spendingTransactions) {
          spendingMap.put(t.getMonth(), spendingMap.get(t.getMonth()) + t.getAmount());
      }

      return spendingMap;
  }

  public int getTimeToSave(double savingGoal) {
    int months = 0;
    totalSpending = 0.0;
    hasMonth = 0;
    HashMap<Integer, Double> monthlySpending = getMonthlySpending(spendingTransactions);
    
    for (int i = 1; i <= 12; i++) {
      totalSpending += monthlySpending.getOrDefault(i, 0.0);
      if (monthlySpending.get(i) > 0) {
        hasMonth++;
      }
    }
    
    return (int) Math.round((savingGoal - totalSpending) % 12);
  }

  public double getAverageSpending() {
      return totalSpending / hasMonth;
  }

  
}
