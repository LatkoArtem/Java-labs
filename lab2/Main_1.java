abstract class Employee {
    protected String name;
    protected double baseSalary;
    protected String task;

    public Employee(String name, double baseSalary, String task) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.task = task;
    }

    public abstract double calculateSalary();
    public abstract void assignedTask();
    public abstract void evaluatePerformance();
}

class Manager extends Employee {
    private int teamSize;
    private static final double TEAM_MEMBER_BONUS = 100.0; // за кожного, хто в команді, вона буде отримувати +100$

    public Manager(String name, double baseSalary, String task, int teamSize) {
        super(name, baseSalary, task);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (teamSize * TEAM_MEMBER_BONUS);
    }

    @Override
    public void assignedTask() {
        System.out.println(name + " (Manager) has a task: " + task);
    }

    @Override
    public void evaluatePerformance() {
        System.out.println(name + " (Manager) evaluates team performance.");
    }
}

class Developer extends Employee {
    private int completedProjects;
    private static final double PROJECT_COMPLETION_BONUS = 200.0; // за кожен виконаний проект отримує +200$

    public Developer(String name, double baseSalary, String task, int completedProjects) {
        super(name, baseSalary, task);
        this.completedProjects = completedProjects;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (completedProjects * PROJECT_COMPLETION_BONUS);
    }

    @Override
    public void assignedTask() {
        if (task == null || task.isEmpty()) {
            System.out.println(name + " (Developer) has no assigned task.");
        } else {
            System.out.println(name + " (Developer) has a task: " + task);
        }
    }

    @Override
    public void evaluatePerformance() {
        System.out.println(name + " (Developer) performance is based on completed projects.");
    }
}

class Designer extends Employee {
    private int completedDesigns;
    private static final double DESIGN_COMPLETION_BONUS = 150.0; // за кожен виконаний дизайн отримує +150$

    public Designer(String name, double baseSalary, String task, int completedDesigns) {
        super(name, baseSalary, task);
        this.completedDesigns = completedDesigns;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (completedDesigns * DESIGN_COMPLETION_BONUS);
    }

    @Override
    public void assignedTask() {
        if (task == null || task.isEmpty()) {
            System.out.println(name + " (Designer) has no assigned task.");
        } else {
            System.out.println(name + " (Designer) has a task: " + task);
        }
    }

    @Override
    public void evaluatePerformance() {
        System.out.println(name + " (Designer) performance is based on completed designs.");
    }
}

public class Main_1 {
    public static void main(String[] args) {
        Employee manager = new Manager("Alice", 5000, "Command and control of the team. Assigns tasks to the team.", 5);
        Employee developer = new Developer("Bob", 4000, "Development, testing and support of programs.", 10 );
        Employee designer = new Designer("Eve", 3700, "Plan, design, develop and create graphic content. Completion of the project.", 8);

        Employee[] employees = {manager, developer, designer};

        System.out.println("--------------------------------------------------------");
        for (Employee employee : employees) {
            System.out.println(employee.name + "'s salary: " + employee.calculateSalary() + "$");
            employee.assignedTask();
            employee.evaluatePerformance();
            System.out.println("--------------------------------------------------------");
        }
    }
}