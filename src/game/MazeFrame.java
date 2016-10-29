package game;
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
	public void turnRight(){
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
	}
	//makes the user turn left
	//no in or out
	public void turnLeft(){
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
	}
	//makes the user go into the next room they are facing if they can
	//no input
	//returns whether or not user entered the room
	public boolean moveForward(){
		switch(player.getDirection()){
		case "N":
			if(this.currentRoom.getN()!=null){
				this.currentRoom=this.currentRoom.getN();
				return true;
			}
			return false;			
		case "W":
			if(this.currentRoom.getW()!=null){
				this.currentRoom=this.currentRoom.getW();
				return true;
			}
			return false;						
		case "S":
			if(this.currentRoom.getS()!=null){
				this.currentRoom=this.currentRoom.getS();
				return true;
			}
			return false;
		case "E":
			if(this.currentRoom.getE()!=null){
				this.currentRoom=this.currentRoom.getE();
				return true;
			}
			return false;						
		}
		return false;
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
	
}
