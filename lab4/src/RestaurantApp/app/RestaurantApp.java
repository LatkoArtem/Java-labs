package RestaurantApp.app;

import RestaurantApp.app.app_menus.MakeOrderInAppMenu;

import java.util.Scanner;

public class RestaurantApp {
    public static void main(String[] args) {
        ConsoleUtils.clearConsole();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Головне меню
            System.out.println("----- Система управління замовленнями в ресторані -----");
            System.out.println("1. Зробити замовлення");
            System.out.println("0. Вихід!");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                ConsoleUtils.clearConsole();
                System.out.println("Вихід з програми!");
                break;
            } else if (choice == 1) {
                ConsoleUtils.clearConsole();
                MakeOrderInAppMenu.makeOrderInApp(scanner);
            } else {
                ConsoleUtils.clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
                System.out.println();
            }
        }
    }
}