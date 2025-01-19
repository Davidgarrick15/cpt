/**
 * author:david
 * date:01/19/2024
 * rental
 */
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
// i got the imports from chat gbt

class Rental {
    public int rentalId;
    public User renter; 
    public Car rentedCar; 
    public LocalDate startDate;
    public LocalDate endDate;
    //local date is from chat gbt
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
    public int getRentalId() {
        return rentalId;//rentalid of the renter
    }
    public void displayRentalDetails() {
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Renter: " + renter.getName());
        System.out.println("Car: " + rentedCar.getMake() + " " + rentedCar.getModel());
        System.out.println("Total Cost: $" + totalCost);
    }
    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            System.out.println("End date cannot be before the start date.");
        } else {
            this.endDate = endDate;
            System.out.println("End date updated successfully.");
        }
    }
    public Car getRentedCar() {
        return rentedCar;
    }
    public Rental[] rentals;  
    public int rentalCount;   // Keeps track of the number of rentals.

    public Rental(int maxRentals) {
        rentals = new Rental[maxRentals];  
        rentalCount = 0;
    }

    public void rentCar(User user, Car car, int days) {
        if (rentalCount >= rentals.length) { // checks the period in which u rent a car
            System.out.println("Rental system is full. Cannot rent more cars.");
            return;
        }

        if (car.getIsAvailable()) {
            car.setIsAvailable(false);
            int rentalId = rentalCount + 1;
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(days);
            //got localdate code from chatgbt
            Rental rental = new Rental(rentalId, user, car, startDate, endDate, 0);
            rental.totalCost = rental.calculateRentalCost();
            rentals[rentalCount] = rental;  // Add rental to the next available slot.
            rentalCount++;
            System.out.println("Car rented successfully to " + user.getName() + " for " + days + " days.");
        } else {
            System.out.println("Sorry, the car is not available.");
        }
    }

    public void returnCar(int rentalId) {
        boolean found = false;
        for (int i = 0; i < rentalCount; i++) {
            if (rentals[i].rentalId == rentalId) {
                rentals[i].rentedCar.setIsAvailable(true);  // Set car as available.
                // Shift elements to fill the gap.
                for (int j = i; j < rentalCount - 1; j++) {
                    rentals[j] = rentals[j + 1];
                }
                rentals[rentalCount - 1] = null;  // Clear the last element.
                rentalCount--;
                System.out.println("Car returned successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Rental with ID " + rentalId + " not found.");
        }
    }

    public void displayAllRentals() {
        if (rentalCount == 0) {
            System.out.println("No rentals to display."); //check of rentals avaiable
        } else {
            System.out.println("Current Rentals:");
            for (int i = 0; i < rentalCount; i++) {
                Rental rental = rentals[i];
                System.out.println("Rental ID: " + rental.rentalId + ", Car: " 
                        + rental.rentedCar.getMake() + " " + rental.rentedCar.getModel() 
                        + ", Renter: " + rental.renter.getName() + ", Total Cost: $" + rental.totalCost);
            }
        }
    }

    
    

    

}