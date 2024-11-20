package RestaurantApp.payment;

// Реалізація способу оплати кредитною карткою.
public class CreditCardPayment implements PaymentSystem {
    @Override
    public void processPayment(String orderId, double amount) {
        System.out.println("Оплата кредитною карткою для замовлення " + orderId + ": " + amount + " грн");
    }
}