package RestaurantApp.order;

import java.util.List;

// Клас створення самого замовлення Order, тобто тут ми вже організовуємо нашу фабрику,
// де будемо давати вже певні параметри, які передаються в Order
public class OrderFactory {
    public static Order createOrder(String type, String orderId, List<String> items) {
        return switch (type) {
            case "DINE_IN" -> new DineInOrder(orderId, items);
            case "TAKEAWAY" -> new TakeawayOrder(orderId, items);
            case "DELIVERY" -> new DeliveryOrder(orderId, items);
            default -> new DineInOrder(orderId, items);
        };
    }
}