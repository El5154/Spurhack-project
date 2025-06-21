import java.util.Scanner;

public class UserInterface{

    private Scanner input = new Scanner(System.in);

    public UserInterface() {

    }

    public void options() {
        System.out.println("Pick an option: \n1. Add Transaction\n2. Remove Transaction\n3. View Spending");
        
    }
}
