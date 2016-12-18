package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*
 * This is the generate maze class
 * it generates the maze from the text file
 * MazeFrame uses the getFirstRoom to get the first room
 * and GenerateMap gets the maze array for use
 */
public class GenerateMaze {
	//general initalizations
	private Room[][] maze;
	private Room startRoom;
	private int numRows;
	private int numColumns;
	private int userHealth;
	private int maxItems;
	public GenerateMaze(){
		String tempString;
		String storage[];
		Room dummy;
		String underScrePrsntStr;
		try{
			BufferedReader in=new BufferedReader(new FileReader("info.txt"));
			tempString=in.readLine();
			//get number of rows and columns from the text file
			numRows=Integer.parseInt(tempString.split(",")[0]);
			numColumns=Integer.parseInt(tempString.split(",")[1]);
			maze=new Room[numColumns][numRows];
			//initalizations for getting the strings from the text files
			//identifiers let you know what is being got
			String name;
			int power;
			ImageIcon itemPicture;
			String element;
			String monsterName;
			ImageIcon monsterPicture;
			ImageIcon monsterPicture2;
			int monsterHealth;
			int monsterPower;
			int monsterMultiplyWeakness;
			String monsterWeakness;
			//for all rooms
			for(int i=0; i<numColumns; i++){
				for(int t=0; t<numRows; t++){
					tempString=in.readLine();
					dummy=new Room();
					tempString=in.readLine();
					//if an item
					if(!tempString.equals("None")){
						//if one item
						if(!tempString.contains("_")){
							storage=tempString.split(",");
							name=storage[0];
							itemPicture=new ImageIcon(storage[1]);
							power=Integer.parseInt(storage[2]);
							element=storage[3];
							//add items to room
							dummy.addItem(new Item(name,power,element,itemPicture));
						}
						//if more than one item
						else{
							underScrePrsntStr=tempString;
							for(int a=0; a<underScrePrsntStr.split("_").length; a++){
								tempString=underScrePrsntStr.split("_")[a];
								storage=tempString.split(",");
								name=storage[0];
								itemPicture=new ImageIcon(storage[1]);
								power=Integer.parseInt(storage[2]);
								element=storage[3];
								//add items to room
								dummy.addItem(new Item(name,power,element,itemPicture));
							}
						}
					}
					tempString=in.readLine();
					//if there is a monster
					if(!tempString.equals("None")){
						if(!tempString.contains("_")){
							storage=tempString.split(",");
							monsterName=storage[0];
							monsterPicture=new ImageIcon(storage[1]);
							monsterPicture2=new ImageIcon(storage[2]);
							monsterHealth=Integer.parseInt(storage[3]);
							monsterPower=Integer.parseInt(storage[4]);
							monsterWeakness=storage[5];
							monsterMultiplyWeakness=Integer.parseInt(storage[6]);
							//add monster to room
							dummy.addMonster(new Monster(monsterHealth,monsterPower,monsterName,monsterWeakness,monsterMultiplyWeakness,monsterPicture,monsterPicture2));
						}
						else{
							underScrePrsntStr=tempString;
							for(int a=0; a<underScrePrsntStr.split("_").length; a++){
								storage=(underScrePrsntStr.split("_")[a]).split(",");
								monsterName=storage[0];
								monsterPicture=new ImageIcon(storage[1]);
								monsterPicture2=new ImageIcon(storage[2]);
								monsterHealth=Integer.parseInt(storage[3]);
								monsterPower=Integer.parseInt(storage[4]);
								monsterWeakness=storage[5];
								monsterMultiplyWeakness=Integer.parseInt(storage[6]);								
								//add monster to room
								dummy.addMonster(new Monster(monsterHealth,monsterPower,monsterName,monsterWeakness,monsterMultiplyWeakness,monsterPicture,monsterPicture2));
							}
						}
					}
					tempString=in.readLine();
					storage=tempString.split(",");
					//add valid room links links
					for(int b=0; b<storage.length; b++){
						dummy.addValidDirection(storage[b]);
					}
					//add the room
					maze[i][t]=dummy;
				}
			}
			tempString=in.readLine();
			this.userHealth=Integer.parseInt(tempString);
			tempString=in.readLine();
			this.startRoom=getRoomAtLocation(tempString);
			tempString=in.readLine();
			this.maxItems=Integer.parseInt(tempString);
			
			in.close();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		attatchLinks();
		
	}

	public Room getRoomAtLocation(String location){		
		int column=Integer.parseInt(location.split(",")[1]);
		int row=Integer.parseInt(location.split(",")[0]);		
		return maze[column][row];
	}
	
	public Room getStartRoom() {
		return startRoom;
	}

	public int getMaxItems(){
		return this.maxItems;
	}

	public void setStartRoom(Room startRoom) {
		this.startRoom = startRoom;
	}



	/*
	 * attatches the links of the rooms
	 */
	public void attatchLinks(){
		for(int i=0; i<numColumns; i++){
			for(int t=0; t<numRows; t++){
				//create the links for each room depending on whether you can go north south east or west
				//if it has a north link
				if(maze[i][t].isValidMovement("N")){					
					maze[i][t].setN(maze[i][t-1]);
				}
				//if it has an East link
				if(maze[i][t].isValidMovement("E")){
					maze[i][t].setE(maze[i+1][t]);
				}

				if(maze[i][t].isValidMovement("S")){
					maze[i][t].setS(maze[i][t+1]);
				}

				if(maze[i][t].isValidMovement("W")){
					maze[i][t].setW(maze[i-1][t]);
				}
			}
		}
	}
	/*
	 * returns an arrayList of all the monsters in the game
	 * needs to have been constructed
	 * no input
	 * returns an arraylist
	 */
	public ArrayList<Monster> getAllMonsters(){
		ArrayList<Monster> returner=new ArrayList<>();
		ArrayList<Monster> dummy;
		for(int i=0; i<numColumns; i++){
			for(int t=0; t<numRows; t++){
				dummy=maze[i][t].getMonsters();
				for(int j=0; j<dummy.size(); j++){
					returner.add(dummy.get(j));
				}
			}
		}
		return returner;
	}
	/*
	 * determines if the user has won by checking to see if all of the monsters in the maze are dead
	 * no input
	 * returns true or false
	 */
	public boolean userHasWon(){
		ArrayList<Monster> allMons=getAllMonsters();
		for(int i=0; i<allMons.size(); i++){
			if(allMons.get(i).getHealth()>0){
				return false;
			}
		}
		return true;
	}
	

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int getUserHealth() {
		return userHealth;
	}

	public void setUserHealth(int userHealth) {
		this.userHealth = userHealth;
	}
	
	

}
