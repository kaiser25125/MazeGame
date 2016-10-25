package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GenerateMap {
	protected GenerateMaze maze;
	protected JFrame jMap;
	protected JPanel jMapper;
	public final static int roomLength=200;
	public final static int roomWidth=200;
	
	public GenerateMap(GenerateMaze m){
		maze=m;
		jMap=new JFrame();
		jMap.setMinimumSize(new Dimension(maze.getNumColumns()*roomWidth,maze.getNumRows()*roomLength));
		jMapper=new JPanel();
		jMapper.setMinimumSize(new Dimension(maze.getNumColumns()*roomWidth,maze.getNumRows()*roomLength));		
		jMapper.setBackground(Color.yellow);
		jMap.add(jMapper);
		CustomPaintRooms locations=new CustomPaintRooms(maze);
		jMapper.add(locations);				
		jMap.setVisible(true);
		jMap.invalidate();
		jMap.repaint();
	}
	
	
		
	public static void main(String[] args){
		GenerateMaze x=new GenerateMaze();
		GenerateMap y=new GenerateMap(x);
	}
}
