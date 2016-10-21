package game;

import javax.swing.ImageIcon;
/*
 * monster class for the game
 * attacks player on a thread (yet to be implemented)
 * if the player is in the same room
 */
public class Monster {
	//health monster has until dies
	private int health;
	//whether or not the monster is alive
	private boolean alive;
	//damage monster deals
	private int power;
	//name of the monster
	private String name;
	//element monster is weak to
	private String element;
	//image of monster
	private ImageIcon image;
	//number to multiply damage by if element of weapon = element
	private int weakness;
	//general constructor
	public Monster(int health, int power, String name, String element,int weakness,ImageIcon image) {
		this.image=image;
		this.health = health;
		this.power = power;
		this.name = name;
		this.element = element;
		this.weakness=weakness;
		this.alive=true;
	}
	//general getters and setters
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	//need to synchronize this function
	//monster takes damage from a weapon
	//need an item
	//alive is set to false if health is < 0
	public void takeDamage(Item weapon){
		if(weapon.getElement().equals(element)){
			this.health=this.health-(weapon.getPower()*this.weakness);
		}
		else{
			this.health=this.health-weapon.getPower();
		}
		if(this.health<=0){
			this.alive=false;
		}
	}
	//need to add an attack function
}
