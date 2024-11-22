package RestaurantApp.order;

import java.util.List;

public class DeliveryOrder extends Order {
    public DeliveryOrder(String orderId, List<String> items) {
        super(orderId, items);
    }

    @Override
    public void processOrder() {
        System.out.println("Замовлення (на доставку) обробляється: " + getItems());
    }
}