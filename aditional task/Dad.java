class Dad extends FamilyMember {
    public Dad(String name) {
        super(name);
    }

    @Override
    public boolean canPerform(Task task) {
        return task instanceof Cleaning || task instanceof Cooking || task instanceof GroceryShopping; // Тато не допомагає з навчанням дітей
    }
}
