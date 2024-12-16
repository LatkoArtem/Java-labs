import java.util.*;

public class FamilyDutiesDistribution {
    public static void main(String[] args) {
        // Завдання
        List<Task> tasks = Arrays.asList(
                new Cleaning(),
                new Cooking(),
                new KidsEducation(),
                new GroceryShopping()
        );

        // Члени сім'ї
        List<FamilyMember> familyMembers = Arrays.asList(
                new Mom("Mom"),
                new Dad("Dad"),
                new IndependentChild("Child")
        );

        // Контролер
        DutiesController controller = new DutiesController(tasks, familyMembers);
        controller.distributeDuties();
    }
}
