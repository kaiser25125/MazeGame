package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Room {
	/*
	 * these are the rooms that make up the maze
	 * has links to all directions
	 * has a single variable for a monster
	 * has a list of items in the room
	 */
	protected Room S;
	protected Room W;
	protected Room E;
	protected Room N;
	protected ArrayList<Monster> monster;
	protected ArrayList<Item> items;
	protected ArrayList<String> validMovements;
	/*
	 * constructor just initalizes
	 */
	public Room(){
		items=new ArrayList<Item>();
		S=null;
		W=null;
		E=null;
		N=null;
		monster=null;
		validMovements=new ArrayList<String>();
		monster=new ArrayList<Monster>();
	}
	//add a valid direction to where the user can move
	//takes string "W"
	//no return
	public void addValidDirection(String k){
		//k should only be W,S,E,N
		validMovements.add(k);
	}
	//detects if valid movement
	//takes string like "W"
	//returns boolean
	public boolean isValidMovement(String s){		
		return validMovements.contains(s);
	}
	//adds an item to the list of items
	//needs an item k
	//returns nothing
	public void addItem(Item k){
		items.add(k);
	}
	/*
	 * removes an item from the list of items
	 * takes an item k
	 * returns nothing
	 */
	public void removeItem(Item k){
		items.remove(k);
	}
	/*
	 * General getters and setters
	 */
	public Room getS() {
		return S;
	}

	public void setS(Room s) {
		S = s;
	}

	public Room getW() {
		return W;
	}

	public void setW(Room w) {
		W = w;
	}

	public Room getE() {
		return E;
	}

	public void setE(Room e) {
		E = e;
	}

	public Room getN() {
		return N;
	}

	public void setN(Room n) {
		N = n;
	}

	public ArrayList<Monster> getMonsters() {
		return monster;
	}

	public void addMonster(Monster monster) {
		this.monster.add(monster);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	//funciton to activate monster thread in the future
	public void activateMonsters(){
		Iterator<Monster> iterator=monster.iterator();
		Monster dummy;
		Thread t1;
		while(iterator.hasNext()){
			dummy=iterator.next();
			if(dummy.getMonsterState()==null || (dummy.getMonsterState() instanceof PausedState)){
				System.out.println("activate monsters");
				dummy.setMonsterState(new AttackingState());
				t1=new Thread(dummy);
				t1.start();
			}
		}
		return;
	}
	//function to join the monster thread in the future
	public void deActivateMonster(){
		//monster.join()
		return;
	}
	
	
	/*
	 * to string that lists valid movements and what room has
	 */
	public String toString(){
		String returner="";
		if(this.N!=null){
			returner=returner+"N:valid ";
		}
		if(this.E!=null){
			returner=returner+"E:valid ";
		}
		if(this.S!=null){
			returner=returner+"S:valid ";
		}
		if(this.W!=null){
			returner=returner+"W:valid ";
		}
		if(this.getMonsters().size()>0){
			for(int i=0;i<this.getMonsters().size(); i++)
			returner=returner+this.getMonsters().get(i).toString()+" ";
		}			
		
		return returner;
	}

}
