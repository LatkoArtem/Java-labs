class Cooking implements Task {
    @Override
    public void perform(String performer) {
        System.out.println(performer + " is cooking dinner.");
    }
}
