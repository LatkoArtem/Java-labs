class Mom extends FamilyMember {
    public Mom(String name) {
        super(name);
    }

    @Override
    public boolean canPerform(Task task) {
        return task instanceof Cleaning || task instanceof Cooking; // Мама не може ходити за покупками
    }
}
