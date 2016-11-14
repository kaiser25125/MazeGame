package game;

public class PausedState extends State {
/*
 * State for when mosnter is not dead but is also not attacking
 */
	public PausedState(JMediator master, CPCMediator painter) {
		super(master, painter);		
	}
	
	public PausedState(){
		
	}
	//return false for stopping the thread
	public boolean doAction(){
		System.out.println("paused");
		return false;
	}
	
}
