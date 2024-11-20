package RestaurantApp.order;

import java.util.List;

public class DineInOrder extends Order {
    public DineInOrder(String orderId, List<String> items) {
        super(orderId, items);
    }

    @Override
    public void processOrder() {
        System.out.println("Замовлення (на місці) обробляється: " + getItems());
    }
}