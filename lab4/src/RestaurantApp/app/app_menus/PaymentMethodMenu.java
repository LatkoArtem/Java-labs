package RestaurantApp.app.app_menus;

import RestaurantApp.app.ConsoleUtils;
import RestaurantApp.payment.CashPayment;
import RestaurantApp.payment.CreditCardPayment;
import RestaurantApp.payment.PaymentSystem;

import java.util.Scanner;

public class PaymentMethodMenu {
    // Вибір способу оплати
    public static PaymentSystem choosePaymentMethodMenu(Scanner scanner) {
        PaymentSystem paymentSystem = null;

        while (paymentSystem == null) {
            System.out.println("Спосіб оплати:");
            System.out.println("1. Готівкою");
            System.out.println("2. Кредитною карткою");
            System.out.println();
            System.out.print("Оберіть спосіб оплати: ");
            int paymentChoice = scanner.nextInt();

            if (paymentChoice == 1) {
                paymentSystem = new CashPayment();
                ConsoleUtils.clearConsole();
            } else if (paymentChoice == 2) {
                paymentSystem = new CreditCardPayment();
                ConsoleUtils.clearConsole();
            } else {
                ConsoleUtils.clearConsole();
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
                System.out.println();
            }
        }

        return paymentSystem;
    }
}
