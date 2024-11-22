package RestaurantApp.app;

import RestaurantApp.kitchen.Kitchen;
import RestaurantApp.order.Order;
import RestaurantApp.order.OrderFactory;
import RestaurantApp.payment.CashPayment;
import RestaurantApp.service.OrderService;
import RestaurantApp.payment.PaymentSystem;
import RestaurantApp.payment.CreditCardPayment;
import RestaurantApp.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {
    public static void main(String[] args) {
        clearConsole();
        Scanner scanner = new Scanner(System.in);

        // Ініціалізація компонентів
        Kitchen kitchen = new Kitchen();

        while (true) {
            // Головне меню
            System.out.println("----- Система управління замовленнями в ресторані -----");
            System.out.println("1. Зробити замовлення");
            System.out.println("0. Вихід!");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                clearConsole();
                System.out.println("Вихід з програми!");
                break;
            } else if (choice == 1) {
                clearConsole();
                makeOrder(scanner, kitchen);
            } else {
                clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
                System.out.println();
            }
        }
    }

    private static void makeOrder(Scanner scanner, Kitchen kitchen) {
        // Список доступного меню
        List<MenuItem> menu = List.of(
                new MenuItem("Піца", 150.0),
                new MenuItem("Салат", 80.0),
                new MenuItem("Суп", 100.0),
                new MenuItem("Чай", 50.0),
                new MenuItem("Десерт", 120.0)
        );

        List<MenuItem> selectedItems = new ArrayList<>(); // Вибрані страви
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
                selectedItems.add(selectedItem); // Додавання страви до замовлення
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
        Order order = OrderFactory.createOrder(orderType, "456", selectedItems.stream().map(MenuItem::getName).toList());

        // Обробка замовлення
        clearConsole();
        System.out.println("----- Ваше замовлення -----");
        for (MenuItem item : selectedItems) {
            System.out.println("• " + item);
        }
        System.out.println("---------------------------");
        System.out.println("Загальна сума: " + totalPrice + " грн");
        System.out.println("---------------------------");
        System.out.println();
        System.out.println("Натисніть ENTER, щоб перейти до оплати...");
        scanner.nextLine(); // Спочатку дочекаємося завершення попереднього вводу
        scanner.nextLine(); // Читаємо фактичне натискання ENTER
        clearConsole();

        // Вибір способу оплати
        PaymentSystem paymentSystem = choosePaymentMethod(scanner);

        // Створення нового OrderService з вибраним способом оплати
        OrderService orderService = new OrderService(kitchen, paymentSystem);

        // Обробка замовлення
        orderService.handleOrder(order, totalPrice); // Передаємо замовлення в сервіс
        System.out.println();
        System.out.println("Натисніть ENTER, щоб повернутися на головний екран...");
        scanner.nextLine(); // Спочатку дочекаємося завершення попереднього вводу
        scanner.nextLine(); // Читаємо фактичне натискання ENTER
        clearConsole();
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

    // Вибір способу оплати
    private static PaymentSystem choosePaymentMethod(Scanner scanner) {
        PaymentSystem paymentSystem = null;

        while (paymentSystem == null) {
            System.out.println("Спосіб оплати:");
            System.out.println("1. Готівкою");
            System.out.println("2. Кредитною карткою");
            System.out.print("Оберіть спосіб оплати: ");
            int paymentChoice = scanner.nextInt();

            if (paymentChoice == 1) {
                paymentSystem = new CashPayment();
                clearConsole();
            } else if (paymentChoice == 2) {
                paymentSystem = new CreditCardPayment();
                clearConsole();
            } else {
                clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
                System.out.println();
            }
        }

        return paymentSystem;
    }

    // Метод для очищення термінала
    private static void clearConsole() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }
}