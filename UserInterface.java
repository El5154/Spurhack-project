import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface{

    private JFrame frame;
    private JPanel mainPanel;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }

    public UserInterface() {

        JFrame frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 700));
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setPreferredSize(new Dimension(720, 35));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(720, 600));
        mainPanel.setPreferredSize(new Dimension(720, 600));

        JButton transactionButton = new JButton("Transaction");
            transactionButton.setBounds(0, 0, 10, 10);
            transactionButton.addActionListener(e -> {
            JButton addButton = new JButton("Add Transaction");
            addButton.setBounds(0, 0, 10, 10);
            addButton.addActionListener(ev -> {   
                // Your logic here
                System.out.println("Add Transaction clicked!");
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
            // Your logic here
            System.out.println("BButton clicked!");
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
            // Your logic here
            System.out.println("SAButton clicked!");
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
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
