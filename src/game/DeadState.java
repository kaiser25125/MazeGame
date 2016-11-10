package game;

public class DeadState extends State {
	
	
	
	public DeadState(JMediator master, CPCMediator painter) {
		super(master, painter);
		// TODO Auto-generated constructor stub
	}

	public DeadState(){}
	public boolean doAction(){
		System.out.println("dead");
		return false;
	}
		
	
}
