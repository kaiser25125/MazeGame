package game;

import java.io.BufferedReader;
import java.io.FileReader;

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
	public GenerateMaze(){
		String temp;
		String storage[];
		Room dummy;
		String underScrePrsnt;
		try{
			BufferedReader in=new BufferedReader(new FileReader("info.txt"));
			temp=in.readLine();
			//get number of rows and columns from the text file
			numRows=Integer.parseInt(temp.split(",")[0]);
			numColumns=Integer.parseInt(temp.split(",")[1]);
			maze=new Room[numColumns][numRows];
			//initalizations for getting the strings from the text files
			//identifiers let you know what is being got
			String name;
			int power;
			ImageIcon itemPicture;
			String element;
			String monsterName;
			ImageIcon monsterPicture;
			int monsterHealth;
			int monsterPower;
			int monsterMultiplyWeakness;
			String monsterWeakness;
			//for all rooms
			for(int i=0; i<numColumns; i++){
				for(int t=0; t<numRows; t++){
					temp=in.readLine();
					dummy=new Room();
					temp=in.readLine();
					//if an item
					if(!temp.equals("None")){
						//if one item
						if(!temp.contains("_")){
							storage=temp.split(",");
							name=storage[0];
							itemPicture=new ImageIcon(storage[1]);
							power=Integer.parseInt(storage[2]);
							element=storage[3];
							//add items to room
							dummy.addItem(new Item(name,power,element,itemPicture));
						}
						//if more than one item
						else{
							underScrePrsnt=temp;
							for(int a=0; a<underScrePrsnt.split("_").length; a++){
								temp=underScrePrsnt.split("_")[a];
								storage=temp.split(",");
								name=storage[0];
								itemPicture=new ImageIcon(storage[1]);
								power=Integer.parseInt(storage[2]);
								element=storage[3];
								//add items to room
								dummy.addItem(new Item(name,power,element,itemPicture));
							}
						}
					}
					temp=in.readLine();
					//if there is a monster
					if(!temp.equals("None")){
						storage=temp.split(",");
						monsterName=storage[0];
						monsterPicture=new ImageIcon(storage[1]);
						monsterHealth=Integer.parseInt(storage[2]);
						monsterPower=Integer.parseInt(storage[3]);
						monsterWeakness=storage[4];
						monsterMultiplyWeakness=Integer.parseInt(storage[5]);
						//add monster to room
						dummy.setMonster(new Monster(monsterHealth,monsterPower,monsterName,monsterWeakness,monsterMultiplyWeakness,monsterPicture));
					}
					temp=in.readLine();
					storage=temp.split(",");
					//add valid room links links
					for(int b=0; b<storage.length; b++){
						dummy.addValidDirection(storage[b]);
					}
					//add the room
					maze[i][t]=dummy;
				}
			}
			temp=in.readLine();
			this.userHealth=Integer.parseInt(temp);
			temp=in.readLine();
			this.startRoom=getRoomAtLocation(temp);
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
	
	

}
