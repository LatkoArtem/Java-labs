abstract class FamilyMember {
    protected String name;

    public FamilyMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canPerform(Task task);
}