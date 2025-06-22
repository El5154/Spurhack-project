import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserInterface{

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private String weekLimit;
    private String monthLimit;
    private String savingGoal;
    private TransactionManager transactionManager = new TransactionManager();
    private Transaction transaction;
    private boolean flag = true;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }

    public UserInterface() {

        frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 700));
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setPreferredSize(new Dimension(720, 35));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(720, 35));
        mainPanel.setPreferredSize(new Dimension(720, 35));

        JButton transactionButton = new JButton("Transaction");
            transactionButton.setBounds(0, 0, 10, 10);
            transactionButton.addActionListener(e -> {
            JButton addButton = new JButton("Add Transaction");
            addButton.setBounds(0, 0, 10, 10);
            addButton.addActionListener(ev -> {   
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

                // Load and resize the icon
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
                    System.out.println("Transaction added: " + transaction.getName());
                } else {
                    System.out.println("Transaction cancelled.");
                }
            });

            JButton removeButton = new JButton("Remove Transaction");
            removeButton.setBounds(0, 0, 10, 10);
            removeButton.addActionListener(ev -> {   
                // Your logic here
                System.out.println("Remove Transaction clicked!");
            });

            JButton editButton = new JButton("Edit Transaction");
            editButton.setBounds(0, 0, 10, 10);
            editButton.addActionListener(ev -> {   
                // Your logic here
                System.out.println("Edit Transaction clicked!");
            });

            mainPanel.removeAll();
            mainPanel.add(addButton);
            mainPanel.add(removeButton);
            mainPanel.add(editButton);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton budgetButton = new JButton("Budget");
        budgetButton.setBounds(12, 0, 10, 10);
        budgetButton.addActionListener(e -> {
            JButton setWeekButton = new JButton("Set Weekly Budget");
            setWeekButton.setBounds(0, 0, 10, 10);
            setWeekButton.addActionListener(ev -> {   
                JOptionPane setBudget = new JOptionPane("Budget Limit");
                setBudget.setBounds(0, 0, 10, 10);
                weekLimit = JOptionPane.showInputDialog(frame, "Set your weekly budget limit:", "Budget Limit", JOptionPane.PLAIN_MESSAGE);
            });

            JButton setMonthButton = new JButton("Set Monthly Budget");
            setMonthButton.setBounds(0, 0, 10, 10);
            setMonthButton.addActionListener(ev -> {   
                JOptionPane setBudget = new JOptionPane("Budget Limit");
                setBudget.setBounds(0, 0, 10, 10);
                monthLimit = JOptionPane.showInputDialog(frame, "Set your monthly budget limit:", "Budget Limit", JOptionPane.PLAIN_MESSAGE);
            });

            mainPanel.removeAll();
            mainPanel.add(setWeekButton);
            mainPanel.add(setMonthButton);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton spendButton = new JButton("View Spending");
        spendButton.setBounds(24, 0, 10, 10);
        spendButton.addActionListener(e -> {
            // Your logic here
            System.out.println("SPButton clicked!");
        });
        
        JButton savingButton = new JButton("Saving Goals");
        savingButton.setBounds(36, 0, 10, 10);
        savingButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            JOptionPane setGoal = new JOptionPane("Saving Goal");
            setGoal.setBounds(0, 0, 10, 10);
            savingGoal = JOptionPane.showInputDialog(frame, "Set your saving goal:", "Saving Goal", JOptionPane.PLAIN_MESSAGE);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(36, 0, 10, 10);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        buttonPanel.add(transactionButton);
        buttonPanel.add(budgetButton);
        buttonPanel.add(spendButton);
        buttonPanel.add(savingButton);
        buttonPanel.add(exitButton);
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}