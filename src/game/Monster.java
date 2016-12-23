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
	
	private ImageIcon image2;
	//number to multiply damage by if element of weapon = element
	private int weakness;
	//int for the starting health of the monster
	private int maxHealth;
	//state for what state the monster is in
	private State monsterState;
		
	
	public Object monsterHealthLock=new Object();
	//general constructor
	public Monster(int health, int power, String name, String element,int weakness,ImageIcon image,ImageIcon image2) {
		this.image=image;
		this.health = health;
		this.maxHealth=health;
		this.power = power;
		this.name = name;
		this.element = element;
		this.weakness=weakness;
		this.alive=true;
		this.image2=image2;
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
	//function for animation
	//switches the image to use to draw
	//no input
	//output is internal change of images
	public void switchImage(){
		ImageIcon dummy=this.image;
		this.image=this.image2;
		this.image2=dummy;
	}
	
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void addCPCObserverToState(CPCObserver painter){
		this.monsterState.setPainter(painter);
	}
	
	public void addGameMediatorToState(GameMediator master){
		this.monsterState.setMaster(master);
	}
	public State getMonsterState() {
		return monsterState;
	}
	/*
	 * sets the monster state to input state
	 * takes a state as input
	 * no output
	 */
	public void setMonsterState(State monsterState) {
		this.monsterState = monsterState;
	}
	
	//returns float for the percent of health the monster still has
	public float getPercentHealth(){
		synchronized(monsterHealthLock){
			float a=this.health;
			float b=this.maxHealth;
			return (a/b);
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
	//run function for thread
	//does the action of the state
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while the state is returning true
		boolean run=true;
			while(run){
				//do action
				run=monsterState.doAction();
				//wait 4 seconds for next attack
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
