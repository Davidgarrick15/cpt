import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory(10);  // Inventory with a maximum of 10 cars.
        LinkedList<Rental> rentals = new LinkedList<>();  // got linkedlist code from chat gbt

        while (true) {
            System.out.println("\nCar Rental System Menu");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Display Available Cars");
            System.out.println("4. Rent a Car");
            System.out.println("5. Return a Car");
            System.out.println("6. Search Car by Make");
            System.out.println("7. Filter Cars by Price");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline.

            switch (choice) {
                case 1:  // Add a Car.
                    System.out.print("Enter Car ID: ");
                    int carId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline.
                    System.out.print("Enter Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Price per Day: ");
                    double price = scanner.nextDouble();
                    Car newCar = new Car(carId, make, model, price);
                    inventory.addCar(newCar);
                    break;

                case 2:  // Remove a Car.
                    System.out.print("Enter Car ID to remove: ");
                    int removeId = scanner.nextInt();
                    inventory.removeCar(removeId);
                    break;

                case 3:  // Display Available Cars.
                    inventory.displayAvailableCars();
                    break;

                case 4:  // Rent a Car.
                    System.out.print("Enter your User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline.
                    System.out.print("Enter your Name: ");
                    String userName = scanner.nextLine();
                    User user = new User(userId, userName, "dummyemail");
                    System.out.print("Enter Car ID to rent: ");
                    int rentCarId = scanner.nextInt();
                    Car carToRent = inventory.getCarById(rentCarId);  // Assume method to get car by ID exists.
                    if (carToRent != null && carToRent.isAvailable()) {
                        carToRent.setAvailable(false);
                        Rental rental = new Rental(rentals.size() + 1, user, carToRent, LocalDate.now(), null, 0);
                        rentals.add(rental);
                        System.out.println("Car rented successfully.");
                    } else {
                        System.out.println("Car is not available.");
                    }
                    break;

                case 5:  // Return a Car.
                    System.out.print("Enter Rental ID to return: ");
                    int rentalId = scanner.nextInt();
                    Rental rentalToReturn = rentals.stream().filter(r -> r.getRentalId() == rentalId).findFirst().orElse(null);
                    if (rentalToReturn != null) {
                        rentalToReturn.setEndDate(LocalDate.now());
                        double cost = rentalToReturn.calculateRentalCost();
                        rentalToReturn.getRentedCar().setAvailable(true);
                        rentals.remove(rentalToReturn);
                        System.out.println("Car returned successfully. Total cost: $" + cost);
                    } else {
                        System.out.println("Rental ID not found.");
                    }
                    break;

                case 6:  // Search by Make.
                    System.out.print("Enter Make to search: ");
                    String searchMake = scanner.nextLine();
                    inventory.searchByMake(searchMake);
                    break;

                case 7:  // Filter by Price.
                    System.out.print("Enter maximum rental price: ");
                    double maxPrice = scanner.nextDouble();
                    inventory.filterByPrice(maxPrice);
                    break;

                case 8:  // Exit.
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
}
