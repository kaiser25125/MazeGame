package game;

import java.awt.Dimension;

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
}
