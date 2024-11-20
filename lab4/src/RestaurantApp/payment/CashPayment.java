package RestaurantApp.payment;


// Реалізація способу оплати готівкою.
public class CashPayment implements PaymentSystem {
    @Override
    public void processPayment(String orderId, double amount) {
        System.out.println("Оплата готівкою для замовлення " + orderId + ": " + amount + " грн");
    }
}