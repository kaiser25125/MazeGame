package game;

import javax.swing.ImageIcon;

public class Monster {
	private int health;
	private boolean alive;
	private int power;
	private String name;
	private String element;
	private ImageIcon image;
	private int weakness;
	public Monster(int health, int power, String name, String element,int weakness,ImageIcon image) {
		this.image=image;
		this.health = health;
		this.power = power;
		this.name = name;
		this.element = element;
		this.weakness=weakness;
		this.alive=true;
	}
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
}
