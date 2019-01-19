import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Wheel extends Thread {

	static int capacity;
	int currentlyOnBoardplayers;
	ArrayList<Player> listofPlayersOnboard = new ArrayList<Player>();
	int maxWaitingTime;
	boolean flag = true;
	
	public Wheel(int maxWaitingTime){
		this.maxWaitingTime=maxWaitingTime;
	}

	// load players
	public synchronized void load_players (Player player){
			listofPlayersOnboard.add(player);
			player.setOnBoard(true);
			capacity++;
			System.out.println("");
			System.out.println("passing player: " + player.getID() +" to the operator.");
			System.out.println("Player "+ player.getID()+"  on board,capacity: " +capacity);
	}

	// run_ride
	public synchronized void  run_ride() {
		if(!listofPlayersOnboard.isEmpty()){
			for(int i=0;i<listofPlayersOnboard.size();i++){
				listofPlayersOnboard.get(i).setRideCompleted(true);
			}
			System.out.println("Threads in this ride are:");
			for (int i = 0; i < listofPlayersOnboard.size(); i++) {
				System.out.print(listofPlayersOnboard.get(i).getID()+",");
			}
		}
	}

	// end the current ride
	public synchronized void end_ride(){
		while(!listofPlayersOnboard.isEmpty()){
			listofPlayersOnboard.remove(0);
		}
		capacity=0;
		
	}
	
	//for setting the waiting time by the operator
	public void setMaxWaitingTime(int maxWaitingTime) {
		this.maxWaitingTime = maxWaitingTime;
	}

	public void run() {
	while(true){
		if(!flag) break; // TODO
		System.out.println("Wheel start sleep.");
		try {
			this.sleep(maxWaitingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Wheel end sleep.");
			run_ride();
			end_ride();
		}
		
	 }
	}

	
 












