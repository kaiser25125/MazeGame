package game;

import javax.swing.ImageIcon;
/*
 * monster class for the game
 * attacks player on a thread (yet to be implemented)
 * if the player is in the same room
 */
public class Monster implements Runnable {
	private Object healthSem;
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
	
	private int maxHealth;
	
	private State monsterState;
	
	public Object monsterHealthLock=new Object();
	//general constructor
	public Monster(int health, int power, String name, String element,int weakness,ImageIcon image) {
		this.image=image;
		this.health = health;
		this.maxHealth=health;
		this.power = power;
		this.name = name;
		this.element = element;
		this.weakness=weakness;
		this.alive=true;
	}
	//general getters and setters
	public int getHealth() {
		synchronized(monsterHealthLock){
			return health;
		}
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
	
	public void addCPCMediatorToState(CPCMediator painter){
		this.monsterState.setPainter(painter);
	}
	
	public void addJMediatorToState(JMediator master){
		this.monsterState.setMaster(master);
	}
	public State getMonsterState() {
		return monsterState;
	}
	public void setMonsterState(State monsterState) {
		this.monsterState = monsterState;
	}
	
	public float getPercentHealth(){
		synchronized(monsterHealthLock){
			return (this.health/this.maxHealth);
		}
	}
	//need to synchronize this function
	//monster takes damage from a weapon
	//need an item
	//alive is set to false if health is < 0
	public void takeDamage(Item weapon){
		synchronized(monsterHealthLock) {
			if(weapon.getElement().equals(element)){
				this.health=this.health-(weapon.getPower()*this.weakness);
			}
			else{
				this.health=this.health-weapon.getPower();
			}
			if(this.health<=0){
				this.alive=false;
				this.setMonsterState(new DeadState());
			}
		}
	}
	//need to add an attack function
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean run=true;
		while(run){
			run=monsterState.doAction();
			try{
				Thread.sleep(4000);
			}
			catch(Exception e){
				e.printStackTrace();
				System.err.println(e);
			}
		}
	}
}
