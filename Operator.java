import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class Operator {
	String file; // imported file
	int max; // max waiting time
	int tot; // total players
	ArrayList<Player> players; // imported players
	ArrayList<Player> players2;
	Wheel w; // instance of wheel
    int addedPlayers;
	boolean flag=false;
	int counter=0;

	
	public Operator(String f)throws IOException,InterruptedException{
		file = f;
		BufferedReader bf = new BufferedReader(new FileReader(file));
		max = Integer.parseInt(bf.readLine());
		tot = Integer.parseInt(bf.readLine());
		players = new ArrayList<Player>(); // max
		players2=new ArrayList<Player>();
		while (true) {
			String temp = bf.readLine();
			if (temp == null) {
				System.out.print(""); // reached end of file
				break;
			}
			else if(temp.equals("")) System.out.print(""); // empty string, do nothing
			else {
				StringTokenizer st=new StringTokenizer(temp,",");
				int id=Integer.parseInt(st.nextToken());
				int waitingTime=Integer.parseInt(st.nextToken());
				Player p=new Player(waitingTime,this,id); // create new player
				p.start();
			}
		}
		bf.close();
		
		w = new Wheel(max);
		w.start();
		
	}
	
	
	
	public synchronized void pleaseQueueMeInTheWheel(Player p){
		addedPlayers++;
		run(p);
	}
	
	public void run(Player p){
			if(w.capacity<5){
				w.load_players(p);
			}
			if(w.capacity==5){
				w.run_ride();
				w.end_ride();
			}
		    if(w.getState()!=State.TIMED_WAITING){
				w.run_ride();
				w.end_ride();
			}
			if(addedPlayers==tot){
				w.run_ride();
				w.end_ride();
				w.stop();
			}		
		
	}

	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Operator OP = new Operator("/Users/israragheb/Desktop/input-output-files/input-2.txt");
	}
}
