package vehicle_rental_system.code;

public class Insurance {
    private String name;
    private String description;

    public Insurance(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}