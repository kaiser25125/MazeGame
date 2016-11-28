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
	//int for max health
	protected int maxHealth;
	protected int maxItems;
	
	public Object userHealthLock=new Object();
	//general constructor
	public User(int health,String direction,int maxItems){
		this.health=health;
		maxHealth=health;
		this.items=new ArrayList<Item>();
		this.alive=true;
		this.direction=direction;
		this.maxItems=maxItems;
	}
	public int getHealth() {		
		synchronized(userHealthLock){
			return health;
		}
	}
	//returns float for how much of health is left
	//no input
	public float getPercentHealth(){
		synchronized(userHealthLock){
			float x=this.health;
			float y=this.maxHealth;
			return x/y;
		}
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	//remove an item from the user's list of items
	public void removeItem(Item remover){
		items.remove(remover);
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
	
	//function for user taking damage
	//sets alive to false if user has no health
	public void takeDamage(int damage){
		synchronized(userHealthLock){
			this.health=this.health-damage;
			if(this.health<=0){
				this.setAlive(false);				
			}		
		}
	}
	
}
