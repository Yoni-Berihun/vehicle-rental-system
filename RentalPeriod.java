package vehicle_rental_system.code;

import java.time.*;

public class RentalPeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public RentalPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public Duration calculateDuration() {
        return Duration.between(startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "From: " + startDateTime.format(formatter) + " To: " + endDateTime.format(formatter);
    }
}