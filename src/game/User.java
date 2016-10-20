package game;

import java.util.ArrayList;

public class User {

	protected int health;
	protected ArrayList<Item> items;
	protected boolean alive;
	protected String direction;
	public User(int health,String direction){
		this.health=health;
		this.items=new ArrayList<Item>();
		this.alive=true;
		this.direction=direction;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public ArrayList<Item> getItems() {
		return items;
	}

	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void addItem(Item k){
		this.items.add(k);
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	//update health bar
	public void takeDamage(int damage){
		this.health=this.health-damage;
		if(this.health<=0){
			this.setAlive(false);
		}		
	}
	
	
}
