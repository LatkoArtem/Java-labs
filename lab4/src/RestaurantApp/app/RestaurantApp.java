package RestaurantApp.app;

import RestaurantApp.kitchen.Kitchen;
import RestaurantApp.order.Order;
import RestaurantApp.order.OrderFactory;
import RestaurantApp.payment.CashPayment;
import RestaurantApp.service.OrderService;
import RestaurantApp.payment.PaymentSystem;
import java.util.Arrays;

public class RestaurantApp {
    public static void main(String[] args) {
        // Ініціалізація компонентів
        Kitchen kitchen = new Kitchen();
        PaymentSystem cashPayment = new CashPayment(); // Оплата готівкою
        OrderService orderService = new OrderService(kitchen, cashPayment);

        // Створення замовлення
        Order order = OrderFactory.createOrder("DELIVERY", "123", Arrays.asList("Піца", "Салат"));

        // Обробка замовлення
        orderService.handleOrder(order, 400.0); // Оплата 400 грн
    }
}