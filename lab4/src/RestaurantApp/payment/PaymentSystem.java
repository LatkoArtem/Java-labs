package RestaurantApp.payment;

// Інтерфейс для системи оплати (тобто містить лише абстрактні класи.
// Це клас, який треба буде реалізувати в окремих класах
public interface PaymentSystem {
    void processPayment(String orderId, double amount);
}