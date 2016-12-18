package game;

public class PausedState extends State {
/*
 * one of the states that the monster can have
 * State for when mosnter is not dead but is also not attacking
 */
	public PausedState(GameMediator master, CPCObserver painter) {
		super(master, painter);		
	}
	
	public PausedState(){
		
	}
	//return false for stopping the thread
	public boolean doAction(){		
		return false;
	}
	
}
