public class Car {
    private int carId;
    private String model;
    private String licensePlate;
    private CarStatus status;
    private CarType type;

    public Car(int carId, String model, String licensePlate, CarStatus status, CarType type) {
        this.carId = carId;
        this.model = model;
        this.licensePlate = licensePlate;
        this.status = status;
        this.type = type;
    }

    public void updateStatus(CarStatus status) {
        this.status = status;
    }

    public void scheduleMaintenance() {
        // Schedule maintenance logic
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
