import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface{

    private JFrame frame;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }

    public UserInterface() {

        JFrame frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setPreferredSize(new Dimension(720, 35));

        JButton transactionButton = new JButton("Transaction");
        transactionButton.setBounds(0, 0, 10, 10);
        transactionButton.addActionListener(e -> {
            // Your logic here
            System.out.println("TButton clicked!");
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

        JButton exitButton = new JButton("Save and Exit");
        exitButton.setBounds(36, 0, 10, 10);
        exitButton.addActionListener(e -> {
            // Your logic here
            System.out.println("EButton clicked!");
        });

        buttonPanel.add(transactionButton);
        buttonPanel.add(budgetButton);
        buttonPanel.add(spendButton);
        buttonPanel.add(savingButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
