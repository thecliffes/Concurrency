/**
 * The primary class for your NightClub, which will have a name, capacity and
 * a manager. People can arrive and leave your club asynchronously once it is
 * opened. Once the manager tries to close the club, the entrance should be
 * closed, then close the exit once everyone has left.
 */
public class NightClub  {
    public String name = "default";
    public boolean isOpen = false;
    private Entrance entrance;
    private Exit exit;
    private Manager manager;
    private int capacity;
    private int peopleCount;
    // TODO Appropriate Thread attributes here
    Thread m;
    Thread en;
    Thread ex;
    
    NightClub (String name, int capacity, Manager manager){
        this.name = name;
        this.capacity = capacity;
        this.manager = manager;
        manager.acceptJob(this);
    }
    public void start() throws InterruptedException
    {
        System.out.println("We are starting club:" + name);
        // TODO Create a thread for the manager
        m = new Thread(manager);
        this.entrance = new Entrance(this,name);
        this.exit = new Exit(this,name);
        // TODO Start a thread for the manager
        m.start();
    }
    public void end() throws InterruptedException{
        this.close();
        // kill the thread that is running the manager
        m.join();
        System.out.println("The simulation has ended.");
    }
    public void open() throws InterruptedException{
        if (!isOpen){
            isOpen = true;
            // TODO Create threads so that users can access or leave the club.
            // Should you also start these threads here?
            ex = new Thread(exit);
            en = new Thread(entrance);
            ex.start();
            en.start();
        }
        else{
            System.out.println("The club is already open!");
        }
    }
    public void close() throws InterruptedException{
        if (isOpen){
            isOpen = false;
            System.out.println("Closing the entrance.");
            // TODO kill the threads that are facilitating accessing and
			// leaving feature.
            en.interrupt();
            ex.interrupt();
            en.join();
            ex.join();
            while(getPeopleCount()>0){
                System.out.println("People are leaving:" + leave());
            }
            System.out.println("Everyone has left the club.");
        }
        else{
            System.out.println("The club is already closed!");
        }
    }
    public int getPeopleCount() {
        return peopleCount;
    }

    public synchronized void enter()
        throws InterruptedException{
        // TODO Must wait for space in a while loop for space in the club.
        while (getPeopleCount() == capacity){
            wait();
        }
        peopleCount++;
        notifyAll();
    }

    public synchronized int leave()
        throws  InterruptedException{
        // TODO Must have enough people to allow this feature. Is there a 
		// condition to check?
        while(getPeopleCount() == 0){
            wait();
        }
        peopleCount--;
        notifyAll();
        return peopleCount;
    }
}
