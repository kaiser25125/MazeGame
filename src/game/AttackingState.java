package game;

public class AttackingState extends State {
	
	
	
	public AttackingState(JMediator master, CPCMediator painter) {
		super(master, painter);
		// TODO Auto-generated constructor stub
	}
	
	public AttackingState(){}

	public boolean doAction(){
		System.out.println("dead");
		return true;
	}
}
