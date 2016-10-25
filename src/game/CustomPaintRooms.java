package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class CustomPaintRooms extends JComponent {
	private GenerateMaze maze;
	public CustomPaintRooms(GenerateMaze m){
		maze=m;
		this.setMinimumSize((new Dimension(maze.getNumColumns()*GenerateMap.roomWidth,maze.getNumRows()*GenerateMap.roomLength)));
		this.setPreferredSize((new Dimension(maze.getNumColumns()*GenerateMap.roomWidth,maze.getNumRows()*GenerateMap.roomLength)));
	}

	public void paint(Graphics g){
		g.setColor(Color.black);		
		for(int i=0; i<maze.getNumColumns(); i++){
			for(int t=0; t<maze.getNumRows(); t++){
				g.fillRect(i*GenerateMap.roomWidth, t*GenerateMap.roomLength, GenerateMap.roomWidth, GenerateMap.roomLength);
			}
		}
	}
}
