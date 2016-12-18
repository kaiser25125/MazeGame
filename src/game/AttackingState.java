package game;

public class AttackingState extends State {
	/*
	 * This is one of the states a monster can have
	 * This one is for when the monster is attacking the user
	 * has the same requirements as state
	 */
	
	/*
	 * this is the constructor
	 * requires the JMediator and the CPCObserver
	 */
	public AttackingState(GameMediator master, CPCObserver painter) {
		super(master, painter);
		// TODO Auto-generated constructor stub
	}
	//dummy constructor
	public AttackingState(){}
	/*
	 * override
	 * this function will attack the user
	 * no input
	 * master and painter must have been set
	 * returns true for making sure the monsters keep attacking
	 */
	public boolean doAction(){		
		//damage user
		master.attackUser(this.getMonster().getPower());
		//call redraw
		painter.reDraw();
		return true;
	}
}
