package game;

public abstract class State {	
	private JMediator master;
	private CPCMediator painter;
	
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

	public boolean doAction(){
		System.err.println("corrupted");
		return false;
	}
}
