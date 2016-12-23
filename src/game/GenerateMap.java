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
	protected GameMediator master;
	//a frame and a panel for the storage
	protected JFrame frame;
	protected JPanel gamePanel;
	private CPCObserver painter;
	//constants for the sizes of the rooms
	public final static int ROOM_LENGTH=100;
	public final static int ROOM_WIDTH=100;
	//constructor for the class
	//takes a generateMaze as input and mazeFrame to know location
	//output is a jFrame of the maze
	public GenerateMap(GameMediator master, CPCObserver painter){
		this.master=master;
		this.painter=painter;
		
		//create frame
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(master.getNumColumns()*ROOM_WIDTH+17, master.getNumRows()*ROOM_LENGTH+40));
		//create panel
		gamePanel=new JPanel();
		//gamePanel.setPreferredSize(new Dimension(maze.getNumColumns()*roomWidth,maze.getNumRows()*roomLength));				
		frame.add(gamePanel, BorderLayout.CENTER);
		//create custom painted component
		CustomPaintMap locations=new CustomPaintMap(master);
		painter.setMap(locations);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(locations, BorderLayout.CENTER);				
		//create frame
		frame.setVisible(true);				
	}		

}
