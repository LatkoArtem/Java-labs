class GroceryShopping implements Task {
    @Override
    public void perform(String performer) {
        System.out.println(performer + " is doing grocery shopping.");
    }
}
