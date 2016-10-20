package game;

public class MazeFrame {
	User player;
	Room currentRoom;	
	
	public MazeFrame(){		
	}

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
			if(this.currentRoom.getS()!=null){
				this.currentRoom=this.currentRoom.getS();
				return true;
			}
			return false;						
		}
		return false;
	}
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
