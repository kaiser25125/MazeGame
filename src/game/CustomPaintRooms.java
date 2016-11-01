package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
/*
 * this is the custom painted component for the map
 * it will create the map inside of a jFrame
 */
public class CustomPaintRooms extends JComponent {
	//need these for observing
	
	private JMediator master;
	//takes a generateMaze and mazeframe as input
	//output is that it paints the maze in a picture form
	public CustomPaintRooms(JMediator master){
		this.master=master;
		
		this.setMinimumSize((new Dimension(master.getNumColumns()*GenerateMap.roomWidth,master.getNumRows()*GenerateMap.roomLength)));
		this.setPreferredSize((new Dimension(master.getNumColumns()*GenerateMap.roomWidth,master.getNumRows()*GenerateMap.roomLength)));
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
				g.fillRect(i*GenerateMap.roomWidth, t*GenerateMap.roomLength, GenerateMap.roomWidth, GenerateMap.roomLength);
				//draw the lines that you can go past
				g.setColor(Color.red);
				//W line
				g.drawLine(i*GenerateMap.roomWidth, t*GenerateMap.roomLength,i*GenerateMap.roomWidth , t*GenerateMap.roomLength+GenerateMap.roomLength);
				//N line
				g.drawLine(i*GenerateMap.roomWidth, t*GenerateMap.roomLength,i*GenerateMap.roomWidth + GenerateMap.roomWidth , t*GenerateMap.roomLength);
				//E line
				g.drawLine(i*GenerateMap.roomWidth + GenerateMap.roomWidth, t*GenerateMap.roomLength,i*GenerateMap.roomWidth +GenerateMap.roomWidth, t*GenerateMap.roomLength + GenerateMap.roomLength);
				//S line
				g.drawLine(i*GenerateMap.roomWidth, t*GenerateMap.roomLength + GenerateMap.roomLength,i*GenerateMap.roomWidth +GenerateMap.roomWidth, t*GenerateMap.roomLength + GenerateMap.roomLength);
				//get the room at this location
				//don't know why I didn't do this first
				currentWorkingOn=master.getRoomAtLocation(Integer.toString(t)+","+Integer.toString(i));
				
				g.setColor(Color.gray);
				//open the lines that are passable
				if(currentWorkingOn.getE()!=null){
					g.drawLine(i*GenerateMap.roomWidth + GenerateMap.roomWidth, t*GenerateMap.roomLength+offSetsForWalls,i*GenerateMap.roomWidth +GenerateMap.roomWidth, t*GenerateMap.roomLength + GenerateMap.roomLength-offSetsForWalls);
				}
				if(currentWorkingOn.getW()!=null){
					g.drawLine(i*GenerateMap.roomWidth, t*GenerateMap.roomLength+offSetsForWalls,i*GenerateMap.roomWidth , t*GenerateMap.roomLength+GenerateMap.roomLength-offSetsForWalls);
				}
				if(currentWorkingOn.getN()!=null){
					g.drawLine(i*GenerateMap.roomWidth + offSetsForWalls, t*GenerateMap.roomLength,i*GenerateMap.roomWidth + GenerateMap.roomWidth -offSetsForWalls, t*GenerateMap.roomLength);
				}
				if(currentWorkingOn.getS()!=null){
					g.drawLine(i*GenerateMap.roomWidth+offSetsForWalls, t*GenerateMap.roomLength + GenerateMap.roomLength,i*GenerateMap.roomWidth +GenerateMap.roomWidth - offSetsForWalls, t*GenerateMap.roomLength + GenerateMap.roomLength);					
				}
				g.setColor(Color.blue);
				//set where user is
				if(currentWorkingOn==master.getCurrentRoom()){
					g.drawString(master.getUserDirection(), i*GenerateMap.roomWidth+GenerateMap.roomWidth/2, t*GenerateMap.roomLength+GenerateMap.roomLength/2);
				}
				//set the items if they are in this room
				if(currentWorkingOn.items.size()>0){
					if(currentWorkingOn.items.size()==1)
						g.setColor(Color.black);
					if(currentWorkingOn.items.size()==2)
						g.setColor(Color.green);
					if(currentWorkingOn.items.size()>2)
						g.setColor(Color.red);
					g.fillRect(i*GenerateMap.roomWidth+(shaveForPosition), GenerateMap.roomLength+t*GenerateMap.roomLength-shaveForPosition, lwItemRect, lwItemRect);					
				}
			}
		}
	}
}
