package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
/*
 * this is the CustomPaintedComponenet for the bottom part of the screen
 * 
 */
public class CustomPaintToolBar extends JComponent implements MouseListener{
	//general initalizations
	private User user;
	private JMediator master;
	private ArrowObserver arrow;
	private CPCMediator painter;
	private Rectangle mapButton;
	private GenerateMap map;
	private ItemListener graphicItems;
	//takes the data and graphics mediator as input
	//output is the custom painted component
	public CustomPaintToolBar(JMediator med,CPCMediator med2){
		master=med;
		//initalize observer for clicking mouse
		this.arrow=new ArrowObserver();
		this.painter=med2;		
		this.graphicItems=new ItemListener();
		//set the size of the bottom
		this.setPreferredSize(new Dimension(GuiMaze.gameWidth,GuiMaze.getToolBarSize()-40));		
		this.addMouseListener(this);
	}
	//paint method for the bottom
	public void paint(Graphics g){
		if(master.getUserPercentHealth()>0 && master.userHasWon()==false){
		this.graphicItems=new ItemListener();
		g.setColor(Color.black);
		//variables for messing with the screen
		int xOffSet=20;
		int yOffSet=30;
		int mapButtonHeight=20;
		int mapButtonWidth=100;
		int mapButtonOffSet=70;
		int healthBarWidth=100;
		int healthBarHeight=10;
		//make the up arrow
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0]=(GuiMaze.gameWidth)-((int)(GuiMaze.gameWidth*.9));
		xPoints[1]=xPoints[0]-xOffSet;
		xPoints[2]=xPoints[0]+xOffSet;
		yPoints[0]=GuiMaze.getToolBarSize()-((int)(GuiMaze.getToolBarSize()*.8));
		yPoints[1]=yPoints[0]+yOffSet;
		yPoints[2]=yPoints[0]+yOffSet;
		Polygon triangle=new Polygon(xPoints, yPoints, 3);
		//store it
		arrow.setUp(triangle);
		//draw it
		g.drawPolygon(triangle);
		//draw the left arrow
		int[] xPoints2 = new int[3];
		int[] yPoints2 = new int[3];
		xPoints2[0]=xPoints[1]-(xOffSet*2);
		xPoints2[1]=xPoints[0]-xOffSet;		
		xPoints2[2]=xPoints[0]-xOffSet;
		yPoints2[1]=yPoints[1];
		yPoints2[2]=yPoints[1]+((int)(yOffSet*1.5));
		yPoints2[0]=yPoints[1]+yOffSet;
		triangle=new Polygon(xPoints2, yPoints2, 3);
		//store it
		arrow.setLeft(triangle);
		//draw it
		g.drawPolygon(triangle);
		//draw the right arrow
		int[] xPoints3 = new int[3];
		int[] yPoints3 = new int[3];		
		xPoints3[0]=xPoints[2]+(xOffSet*2);
		xPoints3[1]=xPoints[0]+xOffSet;		
		xPoints3[2]=xPoints[0]+xOffSet;
		yPoints3[1]=yPoints[1];
		yPoints3[2]=yPoints[1]+((int)(yOffSet*1.5));
		yPoints3[0]=yPoints[1]+yOffSet;		
		triangle=new Polygon(xPoints3, yPoints3, 3);
		//store it
		arrow.setRight(triangle);
		//draw it
		g.drawPolygon(triangle);
		//draw the map button
		mapButton=new Rectangle(GuiMaze.gameWidth-((int)(GuiMaze.gameWidth*.2)),GuiMaze.getToolBarSize()/4,mapButtonWidth,mapButtonHeight);
		g.drawRect(mapButton.x, mapButton.y, mapButton.width, mapButton.height);
		g.drawString("MAP", (int)(mapButton.x+.4*mapButton.width), (int)(mapButton.y+.7*mapButton.height));
		//draw the user health bar below the map button
		g.setColor(Color.green);
		g.drawRect(mapButton.x, mapButton.y+mapButtonOffSet, healthBarWidth, healthBarHeight);
		g.setColor(GuiMaze.getColor(master.getUserPercentHealth()));
		int currentHealth=((int)(healthBarWidth*master.getUserPercentHealth()));
		g.fillRect(mapButton.x, mapButton.y+mapButtonOffSet, currentHealth, healthBarHeight);
		//draw the user's items
		if(master.getUserItems().size()>0){
			ArrayList<Item> items=master.getUserItems();
			Iterator<Item> itemIterator=items.iterator();
			int acc=0;
			int xPosition;
			int yPosition;
			int xSize=50;
			int ySize=50;
			Item currentItem;
			Rectangle listener;
			//for every item
			while(itemIterator.hasNext()){
				//draw it
				currentItem=itemIterator.next();
				xPosition=GuiMaze.gameWidth/3;
				xPosition=xPosition+((GuiMaze.gameWidth/items.size()/3)*acc);
				yPosition=GuiMaze.getToolBarSize()/3;
				listener=new Rectangle(xPosition,yPosition,xSize,ySize);
				graphicItems.addListener(listener);				
				g.drawImage(currentItem.getImage().getImage(), xPosition, yPosition, xSize, ySize,null);				
				acc=acc+1;
			}
		}
		}
	}
	//all mouse click events for the game
	//so far we have up left right and map
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		Item clickedItem;
		if(arrow.UpClicked(arg0.getPoint())){
			System.out.println("up");
			master.moveForward();	
			painter.reDraw();
		}
		if(arrow.LeftClicked(arg0.getPoint())){
			System.out.println("left");
			master.turnLeft();
			painter.reDraw();
		}
		if(arrow.RightClicked(arg0.getPoint())){
			System.out.println("right");
			master.turnRight();
			painter.reDraw();
		}
		
		if(mapButton.contains(arg0.getPoint())){
			System.out.println("map");
			map=new GenerateMap(master,painter);
			painter.reDraw();
		}
		//if user has items
		if(graphicItems.getItems().size()>0){	
			System.out.println("Bottom screen click 2");
			//if user clicked item
			if(graphicItems.getClickedNumber(arg0.getPoint())>-1){
				//if user left clicked attack
				if(SwingUtilities.isLeftMouseButton(arg0)){
					//attack monster, do nothing now
					if(master.hasForwardHall()){
						clickedItem=master.getNumberItemUser(graphicItems.getClickedNumber(arg0.getPoint()));
						master.attackMonsters(clickedItem);
						painter.reDraw();
					}
				}
				//if user right clicked drop the item in the room
				if(SwingUtilities.isRightMouseButton(arg0)){
					if(master.hasForwardHall()){
						clickedItem=master.getNumberItemUser(graphicItems.getClickedNumber(arg0.getPoint()));
						master.removeItemFromUser(clickedItem);
						master.addItemToNextRoom(clickedItem);
						painter.reDraw();
					}
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
	
}
