package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * master class
 * this is the window that actually shows the game being played
 */


public class GuiMaze extends JFrame implements ActionListener,KeyListener {
	//components for the screen	
	private JFrame jWhole;
	private JPanel jTopPanel;
	private JPanel jBottomPanel;
	
	//mediator to get data
	private JMediator master;
	//mediator to repaint
	private CPCMediator repainter;
	//constants for the screen size
	public final static int gameLength=700;
	public final static int gameWidth=900;
	//needs the data mediator as input
	public GuiMaze(JMediator med){
		this.master=med;
		//initalize
		jWhole=new JFrame();
		jWhole.setSize(gameWidth, gameLength);
		jWhole.setLayout(new BorderLayout());
		jTopPanel=new JPanel(new BorderLayout());
		//set color for later
		jTopPanel.setBackground(Color.yellow);				
		//create the bottom part
		jBottomPanel=new JPanel();
		jBottomPanel.setBackground(Color.red);
		jBottomPanel.setPreferredSize(new Dimension(gameWidth,getToolBarSize()));
		jBottomPanel.setLayout(new BorderLayout());
		//create the graphic mediator
		repainter=new CPCMediator();
		//create the bottom graphics
		CustomPaintToolBar bar=new CustomPaintToolBar(master,repainter);	
		repainter.setToolBar(bar);
		jBottomPanel.add(bar,BorderLayout.CENTER);		
		jWhole.add(jBottomPanel,BorderLayout.SOUTH);
		//create the maze pictures
		CustomPaintMaze pictures=new CustomPaintMaze(master,repainter);
		repainter.setRoom(pictures);
		master.setPainter(repainter);
		//add the top part of the screen
		jTopPanel.add(pictures, BorderLayout.CENTER);
		jWhole.add(jTopPanel,BorderLayout.NORTH);
		//add key listener
		jWhole.addKeyListener(this);
		//set the game to be visible
		jWhole.setVisible(true);		
	}
	
	
	//value for setting how big bottom part of the screen is
	public static int getToolBarSize(){
		return GuiMaze.gameLength/3;
	}
	//main
	public static void main(String[] args){
		JMediator master=new JMediator();		
		GuiMaze maze=new GuiMaze(master);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//this is for using the arrow keys to move
		//input is a key event
		//output is the user moving
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub		
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			master.moveForward();
			repainter.reDraw();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			master.turnLeft();
			repainter.reDraw();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			master.turnRight();
			repainter.reDraw();
		}		
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
