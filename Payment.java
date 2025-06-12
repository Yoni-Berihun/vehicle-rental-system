package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.PaymentMethod;
import vehicle_rental_system.code.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {
    private UUID paymentId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;

    public Payment(UUID paymentId, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment ID: " + paymentId.toString().substring(0, 8) +
                ", Amount: $" + amount.setScale(2, BigDecimal.ROUND_HALF_UP) +
                ", Method: " + paymentMethod +
                ", Status: " + status;
    }
}