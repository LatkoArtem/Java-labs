package RestaurantApp.kitchen;

import RestaurantApp.order.Order;

public class Kitchen {
    public void prepareOrder(Order order) {
        System.out.println("Кухня готує замовлення: " + order.getOrderId());
    }
}