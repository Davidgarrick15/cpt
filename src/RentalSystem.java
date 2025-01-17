import java.time.LocalDate;

public class RentalSystem {
    public Rental[] rentals;  
    public int rentalCount;   // Keeps track of the number of rentals.

    public RentalSystem(int maxRentals) {
        rentals = new Rental[maxRentals];  
        rentalCount = 0;
    }

    public void rentCar(User user, Car car, int days) {
        if (rentalCount >= rentals.length) {
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
                rentals[i].rentedCar.setAvailable(true);  // Set car as available.
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
            System.out.println("No rentals to display.");
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
