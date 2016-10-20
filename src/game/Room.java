package game;

import java.util.ArrayList;

public class Room {
	protected Room S;
	protected Room W;
	protected Room E;
	protected Room N;
	protected Monster monster;
	protected ArrayList<Item> items;
	
	public Room(){
		items=new ArrayList<Item>();
		S=null;
		W=null;
		E=null;
		N=null;
		monster=null;
	}
	
	public void addItem(Item k){
		items.add(k);
	}
	
	public void removeItem(Item k){
		items.remove(k);
	}
	
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

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void activateMonster(User player){
		//monster.run(player);
		return;
	}
	public void deActivateMonster(){
		//monster.join()
		return;
	}
	
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
		if(this.getMonster()!=null){
			returner=returner+this.getMonster().getName()+" ";
		}			
		
		return returner;
	}

}
