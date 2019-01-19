import java.util.Date;


public class Player extends Thread{
	
	 int ID;
	 int WaitingTime;
	 boolean OnBoard=false;
	 boolean RideCompleted=false;
	 Operator op;
	 
	 
	 public  Player(int WaitingTime ,Operator O,int ID){
		this.WaitingTime=WaitingTime;
		this.op=O;
	    this.ID=ID; 
	 }
	 
	 public void run(){
		try {
			this.sleep(WaitingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		 System.out.println("player wakes up:"+this.getID());
		 op.pleaseQueueMeInTheWheel(this);
	 }
	
	 public void setRideCompleted(boolean rideComplete) {
				RideCompleted = rideComplete;
	 }

	public void setOnBoard(boolean onBoard) {
		OnBoard = onBoard;
	}

	public int getID() {
		return ID;
	}

	public boolean isRideCompleted() {
		return RideCompleted;
	}

	public boolean isOnBoard() {
		return OnBoard;
	}
	
	

	
		

}
