package game;

public class DeadState extends State {
	/*
	 * one of the states of a monster
	 * this state is for when the monster has been killed by the user
	 */
	
	//super constructor
	public DeadState(GameMediator master, CPCObserver painter) {
		super(master, painter);
	}

	public DeadState(){}
	//doAction for overriding the super
	public boolean doAction(){		
		return false;
	}
		
	
}
