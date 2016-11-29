package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * This file generates the map for the game
 */

public class GenerateMap {
	//need the mediator for information
	protected JMediator master;
	//a frame and a panel for the storage
	protected JFrame frame;
	protected JPanel gamePanel;
	private CPCMediator painter;
	//constants for the sizes of the rooms
	public final static int roomLength=100;
	public final static int roomWidth=100;
	//constructor for the class
	//takes a generateMaze as input and mazeFrame to know location
	//output is a jFrame of the maze
	public GenerateMap(JMediator master, CPCMediator painter){
		//need the mediators for info
		this.master=master;
		this.painter=painter;
		
		//create frame
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(master.getNumColumns()*roomWidth+17, master.getNumRows()*roomLength+40));
		//create panel
		gamePanel=new JPanel();
		//gamePanel.setPreferredSize(new Dimension(maze.getNumColumns()*roomWidth,maze.getNumRows()*roomLength));				
		frame.add(gamePanel, BorderLayout.CENTER);
		//create custom painted component
		CustomPaintRooms locations=new CustomPaintRooms(master);
		painter.setMap(locations);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(locations, BorderLayout.CENTER);				
		//create frame
		frame.setVisible(true);				
	}		
	//testing code don't use	
	/*
	public static void main(String[] args){		
		JMediator med=new JMediator();		
		GenerateMap y=new GenerateMap(med);
		med.turnLeft();
		med.moveForward();
	}
	*/
}
