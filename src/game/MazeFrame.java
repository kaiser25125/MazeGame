package game;

import java.util.ArrayList;

/*
 * mazeframe for controlling the game
 * this is the data that guimaze will have which is the master class
 * has a player and a currentRoom to control the game
 * 
 * need to add method for taking items from room
 */
public class MazeFrame {
	User player;
	Room currentRoom;	
	//general constructor
	public MazeFrame(){		
	}
	//makes the user turn right
	//no in or out
	public void turnRight(JMediator master,CPCMediator painter){
		deActivateNextRoomMonsters(master,painter);
		switch(player.getDirection()){
		case "N":
			player.setDirection("E");
			break;
		case "E":
			player.setDirection("S");
			break;
		case "S":
			player.setDirection("W");
			break;
		case "W":
			player.setDirection("N");
			break;
		}
		activateNextRoomMonsters(master,painter);
	}
	//makes the user turn left
	//no in or out
	public void turnLeft(JMediator master,CPCMediator painter){
		deActivateNextRoomMonsters(master,painter);
		switch(player.getDirection()){
		case "N":
			player.setDirection("W");
			break;
		case "W":
			player.setDirection("S");
			break;
		case "S":
			player.setDirection("E");
			break;
		case "E":
			player.setDirection("N");
			break;
		}
		activateNextRoomMonsters(master,painter);
	}
	//makes the user go into the next room they are facing if they can
	//no input
	//returns whether or not user entered the room
	public boolean moveForward(JMediator master,CPCMediator painter){
		if(hasForwardHall()){
			deActivateNextRoomMonsters(master,painter);
		switch(player.getDirection()){
			case "N":
				this.currentRoom=this.currentRoom.getN();
				return true;
			case "W":
				this.currentRoom=this.currentRoom.getW();
				return true;
			case "S":
				this.currentRoom=this.currentRoom.getS();
				return true;			
			case "E":			
				this.currentRoom=this.currentRoom.getE();
				return true;						
			}
		activateNextRoomMonsters(master,painter);
		}
		return false;
	}
	//returns true if the current room has a passage for the user to the left
	//takes no input
	//output is true or false if it exists
	public boolean hasLeftHall(){
		switch(this.getPlayer().getDirection()){
		case "N":
			return (this.getCurrentRoom().getW()!=null);
		case "W":
			return (this.getCurrentRoom().getS()!=null);
		case "S":
			return (this.getCurrentRoom().getE()!=null);
		case "E":
			return (this.getCurrentRoom().getN()!=null);
		}
		return false;
	}
	
	//returns true if the current room has a passage for the user to the right
	//takes no input
	//output is true or false if it exists	
	public boolean hasRightHall(){
		switch(this.getPlayer().getDirection()){
		case "N":
			return (this.getCurrentRoom().getE()!=null);
		case "E":
			return (this.getCurrentRoom().getS()!=null);
		case "S":
			return (this.getCurrentRoom().getW()!=null);
		case "W":
			return (this.getCurrentRoom().getN()!=null);
		}
		return false;
	}
	//returns true if the current room has a passage for the user to the forward direction
	//takes no input
	//output is true or false if it exists	
	public boolean hasForwardHall(){		
		switch(this.getPlayer().getDirection()){
		case "N":
			return (this.getCurrentRoom().getN()!=null);
		case "E":
			return (this.getCurrentRoom().getE()!=null);
		case "S":
			return (this.getCurrentRoom().getS()!=null);
		case "W":
			return (this.getCurrentRoom().getW()!=null);
		}
		return false;
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has a room forward
	//takes no input
	public boolean nextRoomHasForwardHall(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					return (this.getCurrentRoom().getN().getN()!=null);
				case "E":
					return (this.getCurrentRoom().getE().getE()!=null);
				case "S":
					return (this.getCurrentRoom().getS().getS()!=null);
				case "W":
					return (this.getCurrentRoom().getW().getW()!=null);
			}		
		}
		return false;
	}
	
	//this is for helping the graphics
	//returns whether the room forward of this room has a link for the room to the left
	//takes no input
	public boolean nextRoomHasLeftHall(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					return (this.getCurrentRoom().getN().getW()!=null);
				case "E":
					return (this.getCurrentRoom().getE().getN()!=null);
				case "S":
					return (this.getCurrentRoom().getS().getE()!=null);
				case "W":
					return (this.getCurrentRoom().getW().getS()!=null);
			}		
		}
		return false;
	}

	//this is for helping the graphics
	//returns whether the room forward of this room has a room to the right
	//takes no input
	public boolean nextRoomHasRightHall(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					return (this.getCurrentRoom().getN().getE()!=null);
				case "E":
					return (this.getCurrentRoom().getE().getS()!=null);
				case "S":
					return (this.getCurrentRoom().getS().getW()!=null);
				case "W":
					return (this.getCurrentRoom().getW().getN()!=null);
			}		
		}
		return false;
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has items
	//takes no input
	public boolean nextRoomHasItem(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					return (this.getCurrentRoom().getN().getItems().size()>0);
				case "E":
					return (this.getCurrentRoom().getE().getItems().size()>0);
				case "S":
					return (this.getCurrentRoom().getS().getItems().size()>0);
				case "W":
					return (this.getCurrentRoom().getW().getItems().size()>0);
			}		
		}
		return false;
	}
	//this is for helping the graphics
	//returns the room forward of this room's items
	//takes no input
	public ArrayList<Item> getNextRoomItems(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					return (this.getCurrentRoom().getN().getItems());
				case "E":
					return (this.getCurrentRoom().getE().getItems());
				case "S":
					return (this.getCurrentRoom().getS().getItems());
				case "W":
					return (this.getCurrentRoom().getW().getItems());
			}		
		}
		return null;
	}
	//this is for helping the graphics
	//returns the item at an index of the next rooms items
	//takes an index as input
	//(not the greatest way to do it
	public Item getNumberItemNextRoom(int x){
		ArrayList<Item> items=getNextRoomItems();
		if(items!=null){
			return items.get(x);
		}
		return null;
	}
	public ArrayList<Item> getUserItems(){
		return this.player.getItems();
	}
	
	//getters and setters
	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	//adds an item to the user's list of items
	//takes a item as input
	//no output
	public void addItemtoUser(Item tool){
		this.player.addItem(tool);
	}
	//removes item tool from the next room
	//takes a item as input
	//no output
	public void removeItemFromNextRoom(Item tool){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					this.getCurrentRoom().getN().removeItem(tool);
					break;
				case "E":
					this.getCurrentRoom().getE().removeItem(tool);					
					break;
				case "S":
					this.getCurrentRoom().getS().removeItem(tool);					
					break;
				case "W":
					this.getCurrentRoom().getW().removeItem(tool);					
					break;
			}		
		}
	}
	//adds item tool to the next room
	//takes a item as input
	//no output	
	public void addItemToNextRoom(Item tool){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
				case "N":
					this.getCurrentRoom().getN().addItem(tool);
					break;
				case "E":
					this.getCurrentRoom().getE().addItem(tool);					
					break;
				case "S":
					this.getCurrentRoom().getS().addItem(tool);					
					break;
				case "W":
					this.getCurrentRoom().getW().addItem(tool);					
					break;
			}		
		}
	}
	/*
	 * Activates the monsters in the next room
	 * takes the two mediators as input
	 * no return
	 * starts the threads of the two monsters
	 */
	public void activateNextRoomMonsters(JMediator master,CPCMediator painter){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
			case "N":
				this.getCurrentRoom().getN().activateMonsters(master,painter);
				break;
			case "E":
				this.getCurrentRoom().getE().activateMonsters(master,painter);				
				break;
			case "S":
				this.getCurrentRoom().getS().activateMonsters(master,painter);								
				break;
			case "W":
				this.getCurrentRoom().getW().activateMonsters(master,painter);												
				break;
		}
		}
	}
	
	/*
	 * returns whether or not the next room has monsters
	 * no input
	 */
	public boolean nextRoomHasMonsters(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
			case "N":
				return this.getCurrentRoom().getN().getMonsters().size()>0;				
			case "E":
				return this.getCurrentRoom().getE().getMonsters().size()>0;								
			case "S":
				return this.getCurrentRoom().getS().getMonsters().size()>0;												
			case "W":
				return this.getCurrentRoom().getW().getMonsters().size()>0;																
		}
		}
		return false;
	}

	
	/*
	 * returns a list of the next room's monsters
	 * returns null if there is none
	 * no input
	 */
	public ArrayList<Monster> nextRoomMonsters(){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
			case "N":
				return this.getCurrentRoom().getN().getMonsters();				
			case "E":
				return this.getCurrentRoom().getE().getMonsters();								
			case "S":
				return this.getCurrentRoom().getS().getMonsters();												
			case "W":
				return this.getCurrentRoom().getW().getMonsters();																
		}
		}
		return null;
	}
	/*
	 * de activates the monsters in the next room
	 * takes the mediators as input
	 * returns nothing
	 */
	public void deActivateNextRoomMonsters(JMediator master,CPCMediator painter){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
			case "N":
				this.getCurrentRoom().getN().deActivateMonsters(master,painter);
				break;
			case "E":
				this.getCurrentRoom().getE().deActivateMonsters(master,painter);				
				break;
			case "S":
				this.getCurrentRoom().getS().deActivateMonsters(master,painter);								
				break;
			case "W":
				this.getCurrentRoom().getW().deActivateMonsters(master,painter);												
				break;
		}
		}
	}
	/*
	 * takes an item as input
	 * attacks all of the monsters in the next room
	 * returns nothing
	 * does nothing if no forward room
	 */
	public void attackNextRoomMonsters(Item weapon){
		if(this.hasForwardHall()){
			switch(this.getPlayer().getDirection()){
			case "N":
				this.getCurrentRoom().getN().attackMonsters(weapon);
				break;
			case "E":
				this.getCurrentRoom().getE().attackMonsters(weapon);				
				break;
			case "S":
				this.getCurrentRoom().getS().attackMonsters(weapon);								
				break;
			case "W":
				this.getCurrentRoom().getW().attackMonsters(weapon);												
				break;
		}
		}
	}
	//see user
	public float getUserPercentHealth(){		
		return this.player.getPercentHealth();
	}
	//see user
	public void damageUser(int damage){
		this.player.takeDamage(damage);
	}
	
	//removes an item from the user
	//takes an item as input
	//no output
	public void removeItemFromUser(Item remover){
		this.player.removeItem(remover);
	}
	//this gets an item at a certain index
	//takes an int as an index as input
	//outputs an item
	public Item getNumberItemUser(int x){
		ArrayList<Item> items=getUserItems();
		if(items!=null){
			return items.get(x);
		}
		return null;
	}
	
	public boolean atMaxItems(){
		return this.player.items.size()>=this.player.maxItems;
	}
}
