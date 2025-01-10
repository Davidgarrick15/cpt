public class Car {
    public int carId;
    public String make;
    public String model;
    public double pricePerDay;
    public Car(int carId, String make, String model, double pricePerDay) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.pricePerDay = pricePerDay;
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


}
