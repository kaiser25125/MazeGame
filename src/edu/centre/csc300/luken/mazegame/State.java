package edu.centre.csc300.luken.mazegame;

/*
 * state variable for the monsters
 *	these states determine the behavior that the monster is currently doing
 *there is one for attacking, being paused, and for being dead
 *
 */
public abstract class State {	
	protected GameMediator master;
	protected CPCObserver painter;
	protected Monster monster;
	//takes the GameMediator and CPCObserver as input 
	public State(GameMediator master, CPCObserver painter){		
		this.master=master;
		this.painter=painter;
	}
	
	
	public State(){
	}
	
	
	
	public GameMediator getMaster() {
		return master;
	}


	public void setMaster(GameMediator master) {
		this.master = master;
	}


	public CPCObserver getPainter() {
		return painter;
	}


	public void setPainter(CPCObserver painter) {
		this.painter = painter;
	}
	
	
	
	public Monster getMonster() {
		return monster;
	}


	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	//do action command
	//should never be called except by subclasses
	public boolean doAction(){
		System.err.println("corrupted");
		return false;
	}
}
