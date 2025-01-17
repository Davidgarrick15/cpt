import java.util.ArrayList;

public class Admin extends User {
    public Admin(int userId, String name, String email) {
        super(userId, name, email);
    }
    @Override
    public void displayRole() {
        System.out.println("Admin Role");
    }
    
    public void manageCars() {
        System.out.println("Admin managing cars.");
    }
   
    
}