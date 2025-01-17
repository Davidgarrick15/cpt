import java.time.LocalDate;

public class Customer extends User {
    public Customer(int userId, String name) {
        super(userId, name);
    }

    public void rentCar(Car car, LocalDate startDate, LocalDate endDate) {
        System.out.println("Rented " + car.getMake() + " " + car.getModel() + " from " + startDate + " to " + endDate);
    }
}
