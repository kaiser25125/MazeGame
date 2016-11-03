package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

//dummy to be written
public class CustomPaintMaze extends JComponent {
	JMediator master;
	CPCMediator painter;
	public CustomPaintMaze(JMediator master, CPCMediator painter){
		this.master=master;
		this.painter=painter;
		this.setPreferredSize(new Dimension(GuiMaze.gameWidth,GuiMaze.gameLength-GuiMaze.getToolBarSize()));		
	}
	
	public void paint(Graphics g){
		ImageIcon roomLeft;
		ImageIcon roomForward;
		ImageIcon roomRight;
		//roomLeft=getLeftPic();
		//roomForward=getForwardPic();
		//roomRight=getRightPic();
	}
}
