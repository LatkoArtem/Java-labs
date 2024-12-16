class IndependentChild extends FamilyMember {
    public IndependentChild(String name) {
        super(name);
    }

    @Override
    public boolean canPerform(Task task) {
        return task instanceof Cleaning || task instanceof KidsEducation; // Дитина допомагає лише з прибиранням
    }
}
