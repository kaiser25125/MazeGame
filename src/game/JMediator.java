package game;

import java.util.ArrayList;

/*
 * this is a mediator that will hold the generated maze and the mazeframe
 * 
 */
public class JMediator {
	private GenerateMaze maze;
	private MazeFrame game;
	/*
	 * constructor takes no input because it generates all of the important bits
	 */
	public JMediator(){
		//create the data version of the game
		maze=new GenerateMaze();
		game=new MazeFrame();
		game.setCurrentRoom(maze.getStartRoom());
		game.setPlayer(new User(maze.getUserHealth(),"N"));
	}
	/*
	 * move forward from mazeframe
	 * returns true if the user does
	 */
	public boolean moveForward(){
		return game.moveForward();
	}
	//turns the user left
	public void turnLeft(){		
		game.turnLeft();		
		System.out.println(game.nextRoomHasRightHall());
	}
	//turns the user right
	public void turnRight(){
		game.turnRight();
	}
	//returns number of rows
	public int getNumRows(){
		return maze.getNumRows();
	}
	//returns number of columns of maze
	public int getNumColumns(){
		return maze.getNumColumns();
	}
	//returns number of rows of maze
	public Room getCurrentRoom(){
		return game.getCurrentRoom();
	}
	//returns the room at the location indicated
	public Room getRoomAtLocation(String location){
		return maze.getRoomAtLocation(location); 
	}
	
	public String getUserDirection(){
		return game.getPlayer().getDirection();
	}
	
	public boolean hasLeftHall(){
		return game.hasLeftHall();
	}
	
	public boolean hasRightHall(){
		return game.hasRightHall();
	}
	
	public boolean hasForwardHall(){
		return game.hasForwardHall();
	}
	
	public boolean nextRoomHasForwardHall(){
		return game.nextRoomHasForwardHall();
	}
	
	public boolean nextRoomHasLeftHall(){
		return game.nextRoomHasLeftHall();
	}
	
	public boolean nextRoomHasRightHall(){
		return game.nextRoomHasRightHall();
	}
	public boolean nextRoomHasItem(){
		return game.nextRoomHasItem();
	}
	public ArrayList<Item> getNextRoomItem(){
		return game.getNextRoomItems();
	}
	
	public Item getNumberItemNextRoom(int x){
		return game.getNumberItemNextRoom(x);
	}
	public void addItemToUser(Item itmToAdd){
		game.addItemtoUser(itmToAdd);
	}
	
	public void removeItemFromNextRoom(Item itmToRemove){
		game.removeItemFromNextRoom(itmToRemove);
	}
	
	public ArrayList<Item> getUserItems(){
		return game.getUserItems();
	}
	
	public void addItemToNextRoom(Item tool){
		game.addItemToNextRoom(tool);
	}
	
	public void removeItemFromUser(Item tool){
		game.removeItemFromUser(tool);
	}
	
	public Item getNumberItemUser(int x){
		return game.getNumberItemUser(x);
	}
	
}
