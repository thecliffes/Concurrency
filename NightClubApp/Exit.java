/**
 * The class which will act as the exiting for your nightclub, modelling
 * people leaving your nightclub. 
 */
public class Exit implements  Runnable{
    private NightClub nightClub;
    private int sleepScaler = 10;
    private String name = "Exit";

    Exit (NightClub nightClub){
        this.nightClub = nightClub;
    }

    Exit (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }

    @Override
    public void run() {
        while(true){
            try{
                double random = Math.random();
                Thread.sleep((long) (random*sleepScaler));
                nightClub.leave();
                System.out.println(name + " depart: " + nightClub.getPeopleCount());
            } catch (InterruptedException ex){
                System.out.println("Interrupted Departure Thread");
                return;
            }
        }
    }
    // TODO Does this need an additional method here? Does the class perform a 
	// similar role to the Entrance class?

}
