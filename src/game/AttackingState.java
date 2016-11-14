package game;

public class AttackingState extends State {
	/*
	 * This is the state that is for when the monsters are attacking the user
	 * has the same requirements as state
	 */
	
	/*
	 * this is the constructor
	 * requires the JMediator and the CPCMediator
	 */
	public AttackingState(JMediator master, CPCMediator painter) {
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
		System.out.println("attacking");
		master.attackUser(this.getMonster().getPower());
		painter.reDraw();
		return true;
	}
}
