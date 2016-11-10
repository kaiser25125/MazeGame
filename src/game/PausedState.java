package game;

public class PausedState extends State {

	public PausedState(JMediator master, CPCMediator painter) {
		super(master, painter);		
	}
	
	public PausedState(){
		
	}
	
	public boolean doAction(){
		System.out.println("paused");
		return false;
	}
	
}
