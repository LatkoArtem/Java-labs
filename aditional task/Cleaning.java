class Cleaning implements Task {
    @Override
    public void perform(String performer) {
        System.out.println(performer + " is cleaning the house.");
    }
}
