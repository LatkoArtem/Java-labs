package RestaurantApp.order;

import java.util.List;

public class TakeawayOrder extends Order {
    public TakeawayOrder(String orderId, List<String> items) {
        super(orderId, items);
    }

    @Override
    public void processOrder() {
        System.out.println("Замовлення (на винос) обробляється: " + getItems());
    }
}