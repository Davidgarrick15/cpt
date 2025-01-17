public class Car {
    public int carId;
    public String make;
    public String model;
    public double pricePerDay;
    public boolean isAvailable;




    public Car(int carId, String make, String model, double pricePerDay) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;  
    }
    
    public double getPricePerDay() {
        return pricePerDay;
    }
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void rentCar() {
        if (isAvailable) {
            isAvailable= false;
            System.out.println("Car rented successfully.");
        } else {
            System.out.println("Car is not available.");
        }
    }
    public void returnCar() {
        isAvailable = true;
        System.out.println("Car returned successfully.");
    }
    class LuxuryCar extends Car {
        public boolean hasSunroof;
    
        public LuxuryCar(int carId, String make, String model, double pricePerDay, boolean hasSunroof) {
            super(carId, make, model, pricePerDay);
            this.hasSunroof = hasSunroof;
        }
    
        @Override
        public double getPricePerDay() {
            return super.getPricePerDay() * 1.5;  // Higher rate for luxury.
        }
        
    }
    
  


}
