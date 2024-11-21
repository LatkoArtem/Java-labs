package RestaurantApp.app;

import RestaurantApp.kitchen.Kitchen;
import RestaurantApp.order.Order;
import RestaurantApp.order.OrderFactory;
import RestaurantApp.payment.CashPayment;
import RestaurantApp.service.OrderService;
import RestaurantApp.payment.PaymentSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас для представлення елемента меню
class MenuItem {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " грн";
    }
}

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ініціалізація компонентів
        Kitchen kitchen = new Kitchen();
        PaymentSystem cashPayment = new CashPayment(); // Оплата готівкою
        OrderService orderService = new OrderService(kitchen, cashPayment);

        while (true) {
            // Головне меню
            System.out.println("----- Система управління замовленнями в ресторані -----");
            System.out.println("1. Зробити замовлення");
            System.out.println("0. Вихід!");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                clearConsole();
                System.out.println("Дякуємо, що скористались нашою системою!");
                break;
            } else if (choice == 1) {
                clearConsole();
                makeOrder(scanner, orderService);
            } else {
                clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void makeOrder(Scanner scanner, OrderService orderService) {
        // Список доступного меню
        List<MenuItem> menu = List.of(
                new MenuItem("Піца", 150.0),
                new MenuItem("Салат", 80.0),
                new MenuItem("Суп", 100.0),
                new MenuItem("Напій", 50.0),
                new MenuItem("Десерт", 120.0)
        );

        List<String> selectedItems = new ArrayList<>(); // Вибрані страви
        double totalPrice = 0; // Загальна сума

        while (true) {
            System.out.println("----- Меню ресторану -----");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.println("0. Завершити замовлення");

            System.out.print("Оберіть страву (номер): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                clearConsole();
                break; // Завершення вибору
            } else if (choice > 0 && choice <= menu.size()) {
                MenuItem selectedItem = menu.get(choice - 1);
                selectedItems.add(selectedItem.getName()); // Додавання страви до замовлення
                totalPrice += selectedItem.getPrice();
                clearConsole();
                System.out.println(selectedItem.getName() + " додано до замовлення.");
                System.out.println();
            } else {
                clearConsole();
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
        String orderType = chooseOrderType(scanner);

        // Створення замовлення через OrderFactory
        Order order = OrderFactory.createOrder(orderType, "456", selectedItems);

        // Обробка замовлення
        clearConsole();
        System.out.println("----- Ваше замовлення -----");
        for (String item : selectedItems) {
            System.out.println("- " + item);
        }
        System.out.println("Загальна сума: " + totalPrice + " грн");
        orderService.handleOrder(order, totalPrice); // Передаємо замовлення в сервіс
        System.out.println();
    }

    // Вибрати тип замовлення
    private static String chooseOrderType(Scanner scanner) {
        while (true) {
            System.out.println("Оберіть тип замовлення:");
            System.out.println("1. На місці");
            System.out.println("2. На виніс");
            System.out.println("3. Доставка");
            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return "DINE_IN";
                case 2:
                    return "TAKEAWAY";
                case 3:
                    return "DELIVERY";
                default:
                    clearConsole();
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                    System.out.println();
            }
        }
    }

    // Метод для очищення термінала
    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
