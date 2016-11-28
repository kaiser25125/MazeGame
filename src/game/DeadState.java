package game;

public class DeadState extends State {
	/*
	 * state for when the monster is dead
	 * 
	 */
	
	//super constructor
	public DeadState(JMediator master, CPCMediator painter) {
		super(master, painter);
		// TODO Auto-generated constructor stub
	}

	public DeadState(){}
	//doAction for overriding the super
	public boolean doAction(){		
		return false;
	}
		
	
}
