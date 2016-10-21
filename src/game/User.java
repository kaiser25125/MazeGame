package game;

import java.util.ArrayList;
/*
 * User to play the game
 * has health, list of items as weapons
 * which direction it is facing
 * whether or not the user is alive
 */
public class User {

	protected int health;
	protected ArrayList<Item> items;
	protected boolean alive;
	protected String direction;
	//general constructor
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
	/*
	 * adds a item to the users list of items
	 * takes an item k
	 * returns nothing
	 */
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
	//takes an int damage
	//changes alive to false if health is less than 0
	//need to synchronize this
	public void takeDamage(int damage){
		this.health=this.health-damage;
		if(this.health<=0){
			this.setAlive(false);
		}		
	}
	
	
}
