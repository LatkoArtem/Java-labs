package RestaurantApp.menu;

import java.util.List;

public class MenuProvider {
    public static List<MenuItem> getMenu() {
        return List.of(
                new MenuItem("Піца", 150.0),
                new MenuItem("Салат", 80.0),
                new MenuItem("Суп", 100.0),
                new MenuItem("Чай", 50.0),
                new MenuItem("Десерт", 120.0)
        );
    }
}
