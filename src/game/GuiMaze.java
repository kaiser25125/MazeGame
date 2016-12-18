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
	
	//constant to make bottom panel look better
	public final static int bottomPanelOffSet=40;
	
	//mediator to get data
	private GameMediator master;
	//mediator to repaint
	private CPCObserver repainter;
	//constants for the screen size
	public final static int gameLength=702;
	public final static int gameWidth=900;
	//needs the data mediator as input
	public GuiMaze(GameMediator med){
		this.master=med;
		//initalize the game panel
		jWhole=new JFrame();
		jWhole.setSize(gameWidth, gameLength);
		jWhole.setLayout(new BorderLayout());
		jTopPanel=new JPanel(new BorderLayout());
		
		//set color for later for end screen
		jTopPanel.setBackground(new Color(236, 230, 40));				
		//create the bottom part
		jBottomPanel=new JPanel();
		jBottomPanel.setBackground(new Color(236, 230, 40));
		//temp fix for the bottom panel
		jBottomPanel.setPreferredSize(new Dimension(gameWidth,getToolBarSize()-bottomPanelOffSet));
		jBottomPanel.setLayout(new BorderLayout());
		//create the graphic observer
		repainter=new CPCObserver();
		//create the custom painted component in the bottom of the screen
		CustomPaintToolBar bar=new CustomPaintToolBar(master,repainter);
		//add to observer
		repainter.setToolBar(bar);
		//add bottom graphics to bottom panel
		jBottomPanel.add(bar,BorderLayout.CENTER);
		//add bottom panel to panel
		jWhole.add(jBottomPanel,BorderLayout.SOUTH);
		//create the maze pictures
		CustomPaintMaze pictures=new CustomPaintMaze(master,repainter);
		//set observer for top graphics
		repainter.setRoom(pictures);
		
		//add the graphics observer to the mediator
		master.setPainter(repainter);
		//activate thread for animating monsters
		MonsterAnimationThread animator=new MonsterAnimationThread(repainter,master.getAllMonsters());		
		
		//add the top part of the screen
		jTopPanel.add(pictures, BorderLayout.CENTER);
		jWhole.add(jTopPanel,BorderLayout.NORTH);
		//add key listener
		jWhole.addKeyListener(this);
		//set the game to be visible
		jWhole.setVisible(true);		
		animator.run();
	}
	
	public static Color getColor(Float percent){
		if(percent>=.8){
			return Color.green;
		}
		if(percent>=.4){
			return Color.yellow;
		}
		if(percent>=0){
			return Color.red;
		}
		return Color.black;
	}
	
	//value for setting how big bottom part of the screen is
	public static int getToolBarSize(){
		return GuiMaze.gameLength/3;
	}
	//main
	public static void main(String[] args){
		GameMediator master=new GameMediator();		
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
