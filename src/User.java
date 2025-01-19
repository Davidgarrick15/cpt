/**
 * author:david
 * date:01/19/2024
 * user
 */
import java.time.LocalDate;

public class User {
    public int userId;
    public String name;//user name 
    public String email;// user email
    
    public User(int userId, String name,String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;//name of the user
    }

}
