import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The {@code UserInterface} class provides a graphical user interface (GUI) for a budget tracker application.
 * It allows users to manage transactions, set budget limits, view spending, and define saving goals.
 * The interface is built using Java Swing components and organizes its main features into buttons and panels.
 * <p>
 * Key Features:
 * <ul>
 *   <li>Add, remove, and edit financial transactions (revenue or expense).</li>
 *   <li>Set weekly and monthly budget limits.</li>
 *   <li>View spending information (feature placeholder).</li>
 *   <li>Set and manage saving goals.</li>
 *   <li>Exit the application safely.</li>
 * </ul>
 * <p>
 * The class maintains references to the main application frame, panels, and user input data such as budget limits and saving goals.
 * It also interacts with a {@link TransactionManager} to manage transaction data.
 * 
 * Usage:
 * <pre>
 *     public static void main(String[] args) {
 *         UserInterface ui = new UserInterface();
 *     }
 * </pre>
 * 
 * Dependencies:
 * <ul>
 *   <li>{@link javax.swing.JFrame}</li>
 *   <li>{@link javax.swing.JPanel}</li>
 *   <li>{@link javax.swing.JButton}</li>
 *   <li>{@link javax.swing.JOptionPane}</li>
 *   <li>{@link java.time.LocalDate}</li>
 *   <li>{@link TransactionManager}</li>
 *   <li>{@link Transaction}</li>
 * </ul>
 * 
 * @version 1.0
 */
public class UserInterface{

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel recentPanel;
    private JPanel historyPanel;
    private JPanel savingPanel;
    private int count = 0;
    private String weekLimit;
    private String monthLimit;
    private String savingGoal;
    private TransactionManager transactionManager = new TransactionManager();
    private ArrayList<Transaction> allTransactions;
    private Transaction transaction;
    private Spending spending;
    private HashMap<Integer, Double> spendingMap;
    private boolean flag = true;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }

    public UserInterface() {
        transactionManager.loadTransActions();
        allTransactions = transactionManager.getAllTransactions();
        spending = new Spending();

        frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 700));
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setPreferredSize(new Dimension(720, 35));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setPreferredSize(new Dimension(720, 35));

        recentPanel = new JPanel();
        recentPanel.setBackground(Color.LIGHT_GRAY);
        JTextArea recentTransactions = new JTextArea(5, 20);
        recentTransactions.setLineWrap(true);
        recentTransactions.setEditable(false);
        recentTransactions.append("Recent Transactions:\n");

        JScrollPane scrollPane = new JScrollPane(recentTransactions);

        historyPanel = new JPanel();
        historyPanel.setBackground(Color.LIGHT_GRAY);
        historyPanel.setPreferredSize(new Dimension(200, 700));
        historyPanel.setLayout(new GridLayout(3, 4));
        historyPanel.setVisible(false);

        savingPanel = new JPanel();
        savingPanel.setBackground(Color.LIGHT_GRAY);
        savingPanel.setPreferredSize(new Dimension(200, 700));
        JLabel savingLabel = new JLabel("Time to save for " + savingGoal + ":");

        JButton transactionButton = new JButton("Transaction");
            transactionButton.setBounds(0, 0, 10, 10);
            transactionButton.addActionListener(e -> {
            historyPanel.setVisible(false);
            JButton addButton = new JButton("Add Transaction");
            addButton.setBounds(0, 0, 10, 10);
            addButton.addActionListener(ev -> { 
                flag = true;  
                String name = JOptionPane.showInputDialog(frame, "Enter transaction name:", "Transaction Name", JOptionPane.PLAIN_MESSAGE);
                String amountStr = JOptionPane.showInputDialog(frame, "Enter transaction amount:", "Transaction Amount", JOptionPane.PLAIN_MESSAGE);
                double amount = Double.parseDouble(amountStr);
                final LocalDate[] date = new LocalDate[1];
                while (flag) {
                    try {
                        String dateStr = JOptionPane.showInputDialog(frame, "Enter transaction date (YYYY-MM-DD):", "Transaction Date", JOptionPane.PLAIN_MESSAGE);
                        if (dateStr == null) {
                            flag = false;
                            return;
                        }
                        date[0] = LocalDate.parse(dateStr);
                        flag = false;
                    } catch (java.time.format.DateTimeParseException e1) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please enter a valid date in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                String[] options = {"REVENUE", "EXPENSE"};
                JComboBox<String> categoryDropBox = new JComboBox<>(options);

                javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon("Revenue&Expense.png");
                java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
                javax.swing.ImageIcon customIcon = new javax.swing.ImageIcon(scaledImage);
                int result = JOptionPane.showConfirmDialog(null,categoryDropBox,"Choose a category",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, customIcon);
                if (result == JOptionPane.OK_OPTION) {
                    String selectedCategory = (String) categoryDropBox.getSelectedItem();
                    Transaction.Category category = Transaction.Category.valueOf(selectedCategory.toUpperCase(Locale.ROOT));
                    JOptionPane.showMessageDialog(frame, "You selected: " + category);
                    transaction = new Transaction(name, amount, date[0], category);
                    transactionManager.addTransaction(transaction);
                    recentTransactions.append("Added Transaction: " + transaction.getName() + " on " + transaction.getDate() + "\n");
                }
            });

            JButton removeButton = new JButton("Remove Transaction");
            removeButton.setBounds(0, 0, 10, 10);
            removeButton.addActionListener(ev -> {  
                JComboBox<Transaction> transactionDropBox = new JComboBox<>(allTransactions.toArray(new Transaction[allTransactions.size()]));
                int result = JOptionPane.showConfirmDialog(frame, transactionDropBox, "Select Transaction to Remove", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Transaction selectedTransaction = (Transaction) transactionDropBox.getSelectedItem();
                    if (selectedTransaction != null) {
                        transactionManager.removeTransaction(selectedTransaction);
                        allTransactions.remove(selectedTransaction);
                        JOptionPane.showMessageDialog(frame, "Transaction removed: " + selectedTransaction.getName());
                        recentTransactions.append("Removed Transaction: " + selectedTransaction.getName() + " on " + selectedTransaction.getDate() + "\n");
                    }
                }

            });

            JButton editButton = new JButton("Edit Transaction");
            editButton.setBounds(0, 0, 10, 10);
            editButton.addActionListener(ev -> {   
                JComboBox<Transaction> transactionDropBox = new JComboBox<>(allTransactions.toArray(new Transaction[allTransactions.size()]));
                int result = JOptionPane.showConfirmDialog(frame, transactionDropBox, "Select Transaction to Edit", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Transaction selectedTransaction = (Transaction) transactionDropBox.getSelectedItem();
                    if (selectedTransaction != null) {
                        final String[] newName = { selectedTransaction.getName() };
                        final double[] newAmount = {selectedTransaction.getAmount()};
                        final Transaction.Category[] category = {selectedTransaction.getCategory()};
                        final LocalDate[] newDate = {selectedTransaction.getDate()};

                        JButton nameButton = new JButton("Edit Name");
                        nameButton.setBounds(0, 0, 10, 10);
                        nameButton.addActionListener(evv -> {
                            newName[0] = JOptionPane.showInputDialog(frame, "Enter new transaction name:", selectedTransaction.getName());
                            recentTransactions.append("Updated Transaction Name: " + selectedTransaction.getName() + " to " + newName[0] + "\n");
                            transactionManager.updateName(selectedTransaction, newName[0]);
                        });

                        JButton amountButton = new JButton("Edit Amount");
                        amountButton.setBounds(12, 0, 10, 10);
                        amountButton.addActionListener(evv -> {
                            String newAmountStr = JOptionPane.showInputDialog(frame, "Enter new transaction amount:", selectedTransaction.getAmount());
                            newAmount[0] = Double.parseDouble(newAmountStr);
                            recentTransactions.append("Updated Transaction Amount: " + selectedTransaction.getName() + " to " + newAmount[0] + "\n");
                            transactionManager.updateAmount(selectedTransaction, newAmount[0]);
                        });

                        JButton dateButton = new JButton("Edit Date");
                        dateButton.setBounds(24, 0, 10, 10);
                        dateButton.addActionListener(evv -> {
                            flag = true; 
                            while (flag) {
                                try {
                                    String dateStr = JOptionPane.showInputDialog(frame, "Enter new transaction date (YYYY-MM-DD):", selectedTransaction.getDate());
                                    if (dateStr == null) {
                                        flag = false;
                                        return;
                                    }
                                    newDate[0] = LocalDate.parse(dateStr);
                                    recentTransactions.append("Updated Transaction Date: " + selectedTransaction.getName() + " to " + newDate[0] + "\n");
                                    transactionManager.updateDate(selectedTransaction, newDate[0]);
                                    flag = false;
                                } catch (java.time.format.DateTimeParseException e1) {
                                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please enter a valid date in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                        JButton categoryButton = new JButton("Edit Category");
                        categoryButton.setBounds(36, 0, 10, 10);
                        categoryButton.addActionListener(evv -> {
                            String[] options = {"REVENUE", "EXPENSE"};
                            JComboBox<String> categoryDropBox = new JComboBox<>(options);
                            int categoryResult = JOptionPane.showConfirmDialog(frame, categoryDropBox, "Choose a category", JOptionPane.OK_CANCEL_OPTION);
                            if (categoryResult == JOptionPane.OK_OPTION) {
                                String selectedCategory = (String) categoryDropBox.getSelectedItem();
                                category[0] = Transaction.Category.valueOf(selectedCategory.toUpperCase(Locale.ROOT));
                                recentTransactions.append("Updated Transaction Category: " + selectedTransaction.getName() + " to " + category[0] + "\n");
                                transactionManager.updateCategory(selectedTransaction, category[0]);
                            }
                        });
                        mainPanel.removeAll();
                        mainPanel.add(nameButton);
                        mainPanel.add(amountButton);
                        mainPanel.add(dateButton);
                        mainPanel.add(categoryButton);
                        mainPanel.revalidate();
                        mainPanel.repaint();

                        transactionManager.updateName(selectedTransaction, newName[0]);
                        transactionManager.updateAmount(selectedTransaction, newAmount[0]);
                        transactionManager.updateDate(selectedTransaction, newDate[0]);
                        transactionManager.updateCategory(selectedTransaction, category[0]);
                    }
                }
            });

            mainPanel.removeAll();
            mainPanel.add(addButton);
            mainPanel.add(removeButton);
            mainPanel.add(editButton);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton spendButton = new JButton("View Spending");
        spendButton.setBounds(24, 0, 10, 10);
        spendButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            historyPanel.removeAll();
            historyPanel.revalidate();
            historyPanel.repaint();
            spendingMap = spending.getMonthlySpending(transactionManager.getSpendingTransactions());
            String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
            for (int i = 0; i < 12; i++) {
                JLabel monthLabel = new JLabel(months[i] + ": " + spendingMap.getOrDefault(i + 1, 0.0));
                historyPanel.add(monthLabel);
            }
            historyPanel.setVisible(true);
        });
        
        JButton savingButton = new JButton("Saving Goals");
        savingButton.setBounds(36, 0, 10, 10);
        savingButton.addActionListener(e -> {
            historyPanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            JOptionPane setGoal = new JOptionPane("Saving Goal");
            setGoal.setBounds(0, 0, 10, 10);
            savingGoal = JOptionPane.showInputDialog(frame, "Set your saving goal:", "Saving Goal", JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null, "Your saving goal of " + savingGoal + " will take " + spending.getTimeToSave(Integer.parseInt(savingGoal)) + " months to achieve.");
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(36, 0, 10, 10);
        exitButton.addActionListener(e -> {
            transactionManager.saveTransactions();
            System.exit(0);
        });

        buttonPanel.add(transactionButton);
        buttonPanel.add(spendButton);
        buttonPanel.add(savingButton);
        buttonPanel.add(exitButton);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.EAST);
        frame.add(historyPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                transactionManager.saveTransactions();
            }
        });
    }
}