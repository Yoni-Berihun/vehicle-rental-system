package vehicle_rental_system.code;
public class DriverLicense {
    private String licenseNumber;
    private String issuingCountry;
    private String expiryDate; // Consider using java.time.LocalDate

    public DriverLicense(String licenseNumber, String issuingCountry, String expiryDate) {
        this.licenseNumber = licenseNumber;
        this.issuingCountry = issuingCountry;
        this.expiryDate = expiryDate;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getIssuingCountry() { return issuingCountry; }
    public String getExpiryDate() { return expiryDate; } // Consider returning LocalDate

    @Override
    public String toString() {
        return "License Number: " + licenseNumber + ", Country: " + issuingCountry + ", Expiry: " + expiryDate;
    }
}