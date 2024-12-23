package RestaurantApp.service;

import RestaurantApp.kitchen.Kitchen;
import RestaurantApp.order.Order;
import RestaurantApp.payment.PaymentSystem;

// Обробка замовлень у ресторані
public class OrderService {
    private final Kitchen kitchen; // Це компонент, який буде готувати замовлення.
    private final PaymentSystem paymentSystem; // Це компонент, який буде обробляти оплату за замовлення.

    // ініціалізація об'єктів 'kitchen' і 'paymentSystem'
    public OrderService(Kitchen kitchen, PaymentSystem paymentSystem) {
        this.kitchen = kitchen;
        this.paymentSystem = paymentSystem;
    }

    // Порядок обробки замовлення
    public void handleOrder(Order order, double paymentAmount) {
        try {
            order.processOrder();              // Обробка замовлення
            Thread.sleep(2000);                // Затримка 2 секунди
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.println();

            kitchen.prepareOrder(order);       // Виклик кухні
            Thread.sleep(2000);                // Затримка 2 секунди
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.println();

            paymentSystem.processPayment(order.getOrderId(), paymentAmount); // Оплата
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Це тей самий метод handleOrder тільки без затримок
    /*
    public void handleOrder(Order order, double paymentAmount) {
        order.processOrder();              // Обробка замовлення
        kitchen.prepareOrder(order);       // Виклик кухні
        paymentSystem.processPayment(order.getOrderId(), paymentAmount); // Оплата
    }
    */
}