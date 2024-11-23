package RestaurantApp.app.app_menus;

import RestaurantApp.app.ConsoleUtils;
import RestaurantApp.kitchen.Kitchen;
import RestaurantApp.menu.MenuItem;
import RestaurantApp.menu.MenuProvider;
import RestaurantApp.order.Order;
import RestaurantApp.order.OrderFactory;
import RestaurantApp.order.OrderInformation;
import RestaurantApp.payment.PaymentSystem;
import RestaurantApp.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakeOrderInAppMenu {
    // Зробити замовлення в самому додатку ресторану
    public static void makeOrderInApp(Scanner scanner) {
        List<MenuItem> menu = MenuProvider.getMenu(); // Список доступного меню в ресторані

        List<MenuItem> selectedItems = new ArrayList<>(); // Вибрані страви
        double totalPrice = 0; // Загальна сума

        while (true) {
            System.out.println("----- Меню ресторану -----");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.println("0. Завершити замовлення");

            System.out.println();
            System.out.print("Оберіть страву (номер): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ConsoleUtils.clearConsole();
                break; // Завершення вибору
            } else if (choice > 0 && choice <= menu.size()) {
                MenuItem selectedItem = menu.get(choice - 1);
                selectedItems.add(selectedItem); // Додавання страви до замовлення
                totalPrice += selectedItem.getPrice();
                ConsoleUtils.clearConsole();
                System.out.println(selectedItem.getName() + " додано до замовлення.");
                System.out.println();
            } else {
                ConsoleUtils.clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
                System.out.println();
            }
        }

        if (selectedItems.isEmpty()) {
            System.out.println("Ваше замовлення порожнє.");
            System.out.println();
            return; // Завершити програму
        }

        // Вибір типу замовлення
        String orderType = OrderTypeMenu.chooseOrderType(scanner);
        // Створення замовлення через OrderFactory
        Order order = OrderFactory.createOrder(orderType, "456", selectedItems.stream().map(MenuItem::getName).toList());
        // Вивід інформації про замовлення
        OrderInformation.displayOrderInformation(selectedItems, totalPrice);

        System.out.println();
        System.out.println("Натисніть ENTER, щоб перейти до оплати...");
        scanner.nextLine(); // Спочатку дочекаємося завершення попереднього вводу
        scanner.nextLine(); // Читаємо фактичне натискання ENTER
        ConsoleUtils.clearConsole();

        // Вибір способу оплати
        PaymentSystem paymentSystem = PaymentMethodMenu.choosePaymentMethodMenu(scanner);

        // Ініціалізація компонента Kitchen, бо саме цей компонент буде готувати замовлення
        Kitchen kitchen = new Kitchen();

        // Створення нового OrderService з вибраним способом оплати та тим хто готує замовлення
        OrderService orderService = new OrderService(kitchen, paymentSystem);

        // Обробка замовлення
        orderService.handleOrder(order, totalPrice); // Передаємо замовлення в сервіс
        System.out.println();
        System.out.println("Натисніть ENTER, щоб повернутися до головного меню...");
        scanner.nextLine();
        scanner.nextLine();
        ConsoleUtils.clearConsole();
    }
}
