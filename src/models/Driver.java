// Driver.java
public class Driver{
    private String licenseNumber;
    private boolean isAvailable;

    public Driver(String licenseNumber, boolean isAvailable) {
        this.licenseNumber = licenseNumber;
        this.isAvailable = isAvailable;
    }

    public void assignDelivery() {
        // Assign delivery logic
    }

    public void deliverCar() {
        // Deliver car logic
    }

    public void pickupCar() {
        // Pickup car logic
    }

    public void updateAvailability() {
        // Update availability logic
        this.isAvailable = !this.isAvailable;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
