import java.util.*;

class DutiesController {
    private List<Task> tasks;
    private List<FamilyMember> familyMembers;
    private int currentMemberIndex = 0; // Індекс для чергування

    public DutiesController(List<Task> tasks, List<FamilyMember> familyMembers) {
        this.tasks = tasks;
        this.familyMembers = familyMembers;
    }

    public void distributeDuties() {
        for (Task task : tasks) {
            FamilyMember assignedMember = assignMemberForTask(task);
            task.perform(assignedMember.getName());
        }
    }

    private FamilyMember assignMemberForTask(Task task) {
        int attempts = 0;
        while (attempts < familyMembers.size()) {
            FamilyMember member = familyMembers.get(currentMemberIndex);
            currentMemberIndex = (currentMemberIndex + 1) % familyMembers.size(); // Наступний член сім'ї
            if (member.canPerform(task)) {
                return member; // Член сім'ї може виконати завдання
            }
            attempts++;
        }
        // Якщо ніхто не може виконати завдання, призначаємо наступного члена в черзі
        FamilyMember fallbackMember = familyMembers.get(currentMemberIndex);
        currentMemberIndex = (currentMemberIndex + 1) % familyMembers.size();
        return fallbackMember;
    }
}