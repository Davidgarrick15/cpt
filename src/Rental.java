import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Rental {
    public int rentalId;
    public User renter; 
    public Car rentedCar; 
    public LocalDate startDate;
    public LocalDate endDate;
    public double totalCost;


    public Rental(int rentalId, User renter, Car rentedCar, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.rentalId = rentalId;
        this.renter = renter;
        this.rentedCar = rentedCar;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateRentalCost();
    }
    public double calculateRentalCost(){
        long rentalDuration =  ChronoUnit.DAYS.between(startDate, endDate);
        // i got the chronounit.dyas code from chat gbt
        return rentalDuration * rentedCar.getPricePerDay();
    }

    

}