package RestaurantApp.order;

import RestaurantApp.app.ConsoleUtils;
import RestaurantApp.menu.MenuItem;

import java.util.List;

public class OrderInformation {
    public static void displayOrderInformation(List<MenuItem> selectedItems, double totalPrice) {
        // Вивід інформації про замовлення
        ConsoleUtils.clearConsole();
        System.out.println("----- Ваше замовлення -----");
        for (MenuItem item : selectedItems) {
            System.out.println("• " + item);
        }
        System.out.println("---------------------------");
        System.out.println("Загальна сума: " + totalPrice + " грн");
        System.out.println("---------------------------");
    }
}
