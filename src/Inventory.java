public class Inventory {
    public Car[] carList;
    public int carCount;  // Keeps track of the number of cars in the array.

    public Inventory(int maxCars) {
        carList = new Car[maxCars];  // Fixed-size array.
        carCount = 0;
    }

    public void addCar(Car car) {
        if (carCount < carList.length) {
            carList[carCount] = car;  // Add the car at the next available position.
            carCount++;
            System.out.println("Car added successfully.");
        } else {
            System.out.println("Inventory is full. Cannot add more cars.");
        }
    }
    public void removeCar(int carId) {
        boolean found = false;
        for (int i = 0; i < carCount; i++) {
            if (carList[i].carId == carId) {
                // Shift all cars to the left to fill the gap.
                for (int j = i; j < carCount - 1; j++) {
                    carList[j] = carList[j + 1]; // i got the for loop from chat gbt
                }
                carList[carCount - 1] = null;  
                carCount--;
                found = true;
                System.out.println("Car removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Car with ID " + carId + " not found.");
        }
    }
    public void displayAvailableCars() {
        System.out.println("Available Cars:");
        for (Car car : carList) {
            if (car.getIsAvailable()) {
                System.out.println(car.getMake() + " " + car.getModel() + " - $" + car.getPricePerDay() + " per day");
            }
        }
    }
    public void filterByPrice(double maxPrice) {
        boolean found = false;
        System.out.println("Cars with a rental price below $" + maxPrice + ":");
        for (int i = 0; i < carCount; i++) {
            if (carList[i].getPricePerDay() <= maxPrice) {
                System.out.println(carList[i].getMake() + " " + carList[i].getModel() + " - $" + carList[i].getPricePerDay());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars found below the price of $" + maxPrice);
        }
    }
}
