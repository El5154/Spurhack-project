import java.util.Scanner;

public class UserInterface{

    private Scanner input = new Scanner(System.in);

    public UserInterface() {

    }

    public void options() {
        System.out.println("Pick an option: \n  1. Transaction\n  2. Budget\n  3. View Spending\n  4. Savings Goal\n  5. Save and Exit");
        
    }
}
