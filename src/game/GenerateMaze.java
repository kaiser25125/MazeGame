package game;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;

public class GenerateMaze {
	private Room[][] maze;
	private Room startRoom;
	private int numRows;
	private int numColumns;
	public GenerateMaze(){
		String temp;
		String storage[];
		Room dummy;
		String underScrePrsnt;
		try{
			BufferedReader in=new BufferedReader(new FileReader("info.txt"));
			temp=in.readLine();
			numRows=Integer.parseInt(temp.split(",")[0]);
			numColumns=Integer.parseInt(temp.split(",")[1]);
			maze=new Room[numColumns][numRows];
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
			for(int i=0; i<numColumns; i++){
				for(int t=0; t<numRows; t++){
					temp=in.readLine();
					dummy=new Room();
					temp=in.readLine();
					if(!temp.equals("None")){
						if(!temp.contains("_")){
							storage=temp.split(",");
							name=storage[0];
							itemPicture=new ImageIcon(storage[1]);
							power=Integer.parseInt(storage[2]);
							element=storage[3];
							dummy.addItem(new Item(name,power,element,itemPicture));
						}
						else{
							underScrePrsnt=temp;
							for(int a=0; a<underScrePrsnt.split("_").length; a++){
								temp=underScrePrsnt.split("_")[a];
								storage=temp.split(",");
								name=storage[0];
								itemPicture=new ImageIcon(storage[1]);
								power=Integer.parseInt(storage[2]);
								element=storage[3];
								dummy.addItem(new Item(name,power,element,itemPicture));
							}
						}
					}
					temp=in.readLine();
					if(!temp.equals("None")){
						storage=temp.split(",");
						monsterName=storage[0];
						monsterPicture=new ImageIcon(storage[1]);
						monsterHealth=Integer.parseInt(storage[2]);
						monsterPower=Integer.parseInt(storage[3]);
						monsterWeakness=storage[4];
						monsterMultiplyWeakness=Integer.parseInt(storage[5]);
						dummy.setMonster(new Monster(monsterHealth,monsterPower,monsterName,monsterWeakness,monsterMultiplyWeakness,monsterPicture));
					}
					temp=in.readLine();
					storage=temp.split(",");
					for(int b=0; b<storage.length; b++){
						dummy.addValidDirection(storage[b]);
					}
					maze[i][t]=dummy;
				}
			}
			in.close();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		attatchLinks();
		
	}
	
	public void attatchLinks(){
		for(int i=0; i<numColumns; i++){
			for(int t=0; t<numRows; t++){
				//create the links for each room depending on whether you can go north south east or west
				if(maze[i][t].isValidMovement("N")){					
					maze[i][t].setN(maze[i][t-1]);
				}
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
