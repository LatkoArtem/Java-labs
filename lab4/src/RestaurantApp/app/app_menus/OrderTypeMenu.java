package RestaurantApp.app.app_menus;

import RestaurantApp.app.ConsoleUtils;

import java.util.Scanner;

public class OrderTypeMenu {

    // Вибрати тип замовлення
    public static String chooseOrderType(Scanner scanner) {
        while (true) {
            System.out.println("Оберіть тип замовлення:");
            System.out.println("1. На місці");
            System.out.println("2. На виніс");
            System.out.println("3. Доставка");
            System.out.println();
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
                    ConsoleUtils.clearConsole();
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                    System.out.println();
            }
        }
    }
}
