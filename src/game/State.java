package game;

public abstract class State {	
	protected JMediator master;
	protected CPCMediator painter;
	protected Monster monster;
	
	public State(JMediator master, CPCMediator painter){		
		this.master=master;
		this.painter=painter;
	}
	
	
	public State(){
	}
	
	
	
	public JMediator getMaster() {
		return master;
	}


	public void setMaster(JMediator master) {
		this.master = master;
	}


	public CPCMediator getPainter() {
		return painter;
	}


	public void setPainter(CPCMediator painter) {
		this.painter = painter;
	}
	
	
	
	public Monster getMonster() {
		return monster;
	}


	public void setMonster(Monster monster) {
		this.monster = monster;
	}


	public boolean doAction(){
		System.err.println("corrupted");
		return false;
	}
}
