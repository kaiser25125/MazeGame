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
	//returns true if the current room has a passage for the user to the left
	//takes no input
	//output is true or false if it exists
	public boolean hasLeftHall(){
		return game.hasLeftHall();
	}
	//returns true if the current room has a passage for the user to the right
	//takes no input
	//output is true or false if it exists	
	public boolean hasRightHall(){
		return game.hasRightHall();
	}
	//returns true if the current room has a passage for the user to the forward direction
	//takes no input
	//output is true or false if it exists	
	public boolean hasForwardHall(){
		return game.hasForwardHall();
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has a room forward
	//takes no input
	public boolean nextRoomHasForwardHall(){
		return game.nextRoomHasForwardHall();
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has a link for the room to the left
	//takes no input
	public boolean nextRoomHasLeftHall(){
		return game.nextRoomHasLeftHall();
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has a room to the right
	//takes no input	
	public boolean nextRoomHasRightHall(){
		return game.nextRoomHasRightHall();
	}
	//this is for helping the graphics
	//returns whether the room forward of this room has items
	//takes no input
	public boolean nextRoomHasItem(){
		return game.nextRoomHasItem();
	}
	//this is for helping the graphics
	//returns the room forward of this room's items
	//takes no input
	public ArrayList<Item> getNextRoomItem(){
		return game.getNextRoomItems();
	}
	//this is for helping the graphics
	//returns the item at an index of the next rooms items
	//takes an index as input
	//(not the greatest way to do it
	public Item getNumberItemNextRoom(int x){
		return game.getNumberItemNextRoom(x);
	}
	//adds an item to the user's list of items
	//takes a item as input
	//no output
	public void addItemToUser(Item itmToAdd){
		game.addItemtoUser(itmToAdd);
	}
	//removes item tool from the next room
	//takes a item as input
	//no output
	public void removeItemFromNextRoom(Item itmToRemove){
		game.removeItemFromNextRoom(itmToRemove);
	}
	
	public ArrayList<Item> getUserItems(){
		return game.getUserItems();
	}
	//adds item tool to the next room
	//takes a item as input
	//no output	
	public void addItemToNextRoom(Item tool){
		game.addItemToNextRoom(tool);
	}
	//removes an item from the user
	//takes an item as input
	//no output
	public void removeItemFromUser(Item tool){
		game.removeItemFromUser(tool);
	}
	//this gets an item at a certain index
	//takes an int as an index as input
	//outputs an item
	public Item getNumberItemUser(int x){
		return game.getNumberItemUser(x);
	}
	
}
