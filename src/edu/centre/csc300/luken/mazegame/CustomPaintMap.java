package edu.centre.csc300.luken.mazegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
/*
 * this is the custom painted component for the map
 * it will create the map inside of a jFrame
 */
public class CustomPaintMap extends JComponent {
	//need these for observing
	
	private GameMediator master;
	//takes a generateMaze and mazeframe as input
	//output is that it paints the maze in a picture form
	public CustomPaintMap(GameMediator master){
		this.master=master;
		
		this.setMinimumSize((new Dimension(master.getNumColumns()*GenerateMap.ROOM_WIDTH,master.getNumRows()*GenerateMap.ROOM_LENGTH)));
		this.setPreferredSize((new Dimension(master.getNumColumns()*GenerateMap.ROOM_WIDTH,master.getNumRows()*GenerateMap.ROOM_LENGTH)));
	}
	//general paint method
	public void paint(Graphics g){
		//variable for moving item square
		int shaveForPosition=15;
		//variable for size of item square
		int lwItemRect=10;
		//variable for those little red lines that show there is no wall
		int offSetsForWalls=10;
		g.setColor(Color.gray);
		Room currentWorkingOn;
		for(int i=0; i<master.getNumColumns(); i++){
			for(int t=0; t<master.getNumRows(); t++){
				g.setColor(Color.gray);
				//draw the room
				g.fillRect(i*GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH, GenerateMap.ROOM_WIDTH, GenerateMap.ROOM_LENGTH);
				//draw the lines that you can go past
				g.setColor(Color.red);
				//W line
				g.drawLine(i*GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH , t*GenerateMap.ROOM_LENGTH+GenerateMap.ROOM_LENGTH);
				//N line
				g.drawLine(i*GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH + GenerateMap.ROOM_WIDTH , t*GenerateMap.ROOM_LENGTH);
				//E line
				g.drawLine(i*GenerateMap.ROOM_WIDTH + GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH +GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH);
				//S line
				g.drawLine(i*GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH +GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH);
				//get the room at this location
				//don't know why I didn't do this first
				currentWorkingOn=master.getRoomAtLocation(Integer.toString(t)+","+Integer.toString(i));
				
				g.setColor(Color.gray);
				//open the lines that are passable
				if(currentWorkingOn.getE()!=null){
					g.drawLine(i*GenerateMap.ROOM_WIDTH + GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH+offSetsForWalls,i*GenerateMap.ROOM_WIDTH +GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH-offSetsForWalls);
				}
				if(currentWorkingOn.getW()!=null){
					g.drawLine(i*GenerateMap.ROOM_WIDTH, t*GenerateMap.ROOM_LENGTH+offSetsForWalls,i*GenerateMap.ROOM_WIDTH , t*GenerateMap.ROOM_LENGTH+GenerateMap.ROOM_LENGTH-offSetsForWalls);
				}
				if(currentWorkingOn.getN()!=null){
					g.drawLine(i*GenerateMap.ROOM_WIDTH + offSetsForWalls, t*GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH + GenerateMap.ROOM_WIDTH -offSetsForWalls, t*GenerateMap.ROOM_LENGTH);
				}
				if(currentWorkingOn.getS()!=null){
					g.drawLine(i*GenerateMap.ROOM_WIDTH+offSetsForWalls, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH,i*GenerateMap.ROOM_WIDTH +GenerateMap.ROOM_WIDTH - offSetsForWalls, t*GenerateMap.ROOM_LENGTH + GenerateMap.ROOM_LENGTH);					
				}
				g.setColor(Color.blue);
				//set where user is
				if(currentWorkingOn==master.getCurrentRoom()){
					g.drawString(master.getUserDirection(), i*GenerateMap.ROOM_WIDTH+GenerateMap.ROOM_WIDTH/2, t*GenerateMap.ROOM_LENGTH+GenerateMap.ROOM_LENGTH/2);
				}
				//set the items if they are in this room
				if(currentWorkingOn.items.size()>0){
					if(currentWorkingOn.items.size()==1)
						g.setColor(Color.black);
					if(currentWorkingOn.items.size()==2)
						g.setColor(Color.green);
					if(currentWorkingOn.items.size()>2)
						g.setColor(Color.red);
					g.fillRect(i*GenerateMap.ROOM_WIDTH+(shaveForPosition), GenerateMap.ROOM_LENGTH+t*GenerateMap.ROOM_LENGTH-shaveForPosition, lwItemRect, lwItemRect);					
				}
			}
		}
	}
}
