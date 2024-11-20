package RestaurantApp.order;

import java.util.List;

public abstract class Order {
    private final String orderId; // Унікальний ідентифікатор замовлення
    private final List<String> items; // Список замовлення (наприклад, "Піца", "Напій")

    // Ініціалізація полів класу. Це важливо для правильного створення об'єктів цього класу.
    public Order(String orderId, List<String> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public abstract void processOrder(); // Абстрактний метод для обробки

    // Методи доступу(гетери). Ці методи дають доступ до приватних полів класу, але не дозволяють змінювати їх напряму.
    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return items;
    }
}