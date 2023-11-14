/**
 * The class which will act as the entrance for your nightclub, modelling
 * people arriving at and entering your nightclub. 
 */
public class Entrance implements  Runnable{
    final private NightClub nightClub;
    private int sleepScaler = 10;
    private String name = "Entrance";
    Entrance (NightClub nightClub){
        this.nightClub = nightClub;
    }
    Entrance (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }

    @Override
    public void run() {
        // TODO Enter code for how the entrance to be used.
        while(true){
            try {
                double random = Math.random();
                Thread.sleep((long) (random*sleepScaler));
                nightClub.enter();
                System.out.println(name + " enter: " + nightClub.getPeopleCount());
            } catch(InterruptedException ex){
                System.out.println("Interrupted Arrival Thread");
                return;
            }
        }
    }

}
