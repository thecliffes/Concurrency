public class Application {
    public static void main(String[] args) throws InterruptedException {
        String name = "Fiction";
        int capacity = 20;
        Manager manager = new Manager("John Doe");
        NightClub fiction = new NightClub(name, capacity, manager);

        fiction.start();
        Thread.sleep(60000);
        fiction.end();
    }
}
