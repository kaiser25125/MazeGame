package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

//dummy to be written
//x size needs to be determined mathmatecially
//y size needs to be determined mathmatecially
/*
 * This class draws the maze walls on the top part
 * also contains the mouse listener for the items in the maze
 */
public class CustomPaintMaze extends JComponent implements MouseListener {
	JMediator master;
	CPCMediator painter;
	ItemListener itemObservers;
	/*
	 * Takes the two mediators as inputs
	 * outputs the custom painted object
	 */
	public CustomPaintMaze(JMediator master, CPCMediator painter){
		this.master=master;
		this.painter=painter;
		this.setPreferredSize(new Dimension(GuiMaze.gameWidth,GuiMaze.gameLength-GuiMaze.getToolBarSize()));
		this.addMouseListener(this);
	}
	/*
	 * Paint function for repainting the maze every time
	 */
	public void paint(Graphics g){
		if(master.getUserPercentHealth()>0 && master.userHasWon()==false){
		int yOffSet=10;
		//initalizations
		ImageIcon roomLeft;
		ImageIcon roomForward;
		ImageIcon roomRight;
		//get the right picture, the middle, and the right
		roomLeft=getLeftPic();
		roomForward=getForwardPic();
		roomRight=getRightPic();
		//set up observers for items
		itemObservers=new ItemListener();
		//draw the walls
		g.drawImage(roomLeft.getImage(), 0, 0, GuiMaze.gameWidth/3, GuiMaze.gameLength-GuiMaze.getToolBarSize(), null);
		g.drawImage(roomForward.getImage(), GuiMaze.gameWidth/3, 0, GuiMaze.gameWidth/3, GuiMaze.gameLength-GuiMaze.getToolBarSize(), null);
		g.drawImage(roomRight.getImage(), GuiMaze.gameWidth-GuiMaze.gameWidth/3, 0, GuiMaze.gameWidth/3, GuiMaze.gameLength-GuiMaze.getToolBarSize(), null);
		//draw the monsters for the game
		if(master.nextRoomHasMonster()){			
			Iterator<Monster> iterator=master.getNextRoomMonsters().iterator();
			Monster currentMonster;
			//values for processing
			int acc=0;
			int xPosition;
			int yPosition;
			//"constant" for the size of the monsters
			int xSize=100;
			int ySize=100;
			//rect for healthbar
			Rectangle healthBar;
			int healthWidth;
			//parsing float
			float healthParse=xSize;
			//while there is a next monster
			while(iterator.hasNext()){				
				currentMonster=iterator.next();
				xPosition=0;
				yPosition=0;
				xSize=100;
				ySize=100;
				healthParse=xSize;
				healthWidth=0;
				//if the monster is not dead
				if(!(currentMonster.getMonsterState() instanceof DeadState)){
					//draw all of the monsters
					xPosition=GuiMaze.gameWidth/3;
					xPosition=xPosition+((GuiMaze.gameWidth/master.getNextRoomMonsters().size()/3)*acc);
					yPosition=GuiMaze.gameLength-GuiMaze.getToolBarSize()-GuiMaze.gameLength/4;
					g.drawImage(currentMonster.getImage().getImage(), xPosition, yPosition, xSize, ySize,null);
					
					//draw the health bars
					yPosition=yPosition-yOffSet;
					//draw element weak to
					g.setColor(Color.white);
					g.fillRect(xPosition, yPosition-2*yOffSet, xSize, ySize/8);
					g.setColor(Color.black);
					g.drawString(currentMonster.getElement(), xPosition+25, yPosition-yOffSet);
					//draw outer green
					g.setColor(Color.green);
					g.drawRect(xPosition, yPosition, xSize, ySize/10);
					g.setColor(GuiMaze.getColor(currentMonster.getPercentHealth()));
					//draw the actual health
					healthParse=healthParse*currentMonster.getPercentHealth();					
					healthWidth=(int)healthParse;						
					g.fillRect(xPosition, yPosition, healthWidth, ySize/10);


					acc=acc+1;
				}
			}
		}
		//if the next room has items
		if(master.nextRoomHasItem()){
			ArrayList<Item> items=master.getNextRoomItem();
			Iterator<Item> itemIterator=items.iterator();
			int acc2=0;
			int xPosition2;
			int yPosition2;
			int xSize2=50;
			int ySize2=50;
			Item currentItem;
			Rectangle listener;
			//for each item
			while(itemIterator.hasNext()){
				//get the next item
				currentItem=itemIterator.next();
				//calculate where to put it
				xPosition2=GuiMaze.gameWidth/3;
				xPosition2=xPosition2+((GuiMaze.gameWidth/items.size()/3)*acc2);
				yPosition2=GuiMaze.gameLength-GuiMaze.getToolBarSize()-GuiMaze.gameLength/7;
				//create listener for item
				listener=new Rectangle(xPosition2,yPosition2,xSize2,ySize2);
				itemObservers.addListener(listener);
				//draw it
				g.drawImage(currentItem.getImage().getImage(), xPosition2, yPosition2, xSize2, ySize2,null);				
				acc2=acc2+1;
			}
		}
		}
		//if the user has won the game
		else if(master.userHasWon()){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g.drawString("You Win!!!!", this.getX()+(this.getWidth()/10), this.getY()+this.getHeight()/2);
		}
		//if the user died
		else if(!master.userHasWon()){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g.drawString("You Lose!!!!", this.getX()+(this.getWidth()/10), this.getY()+this.getHeight()/2);
		}
	}
	/*
	 * This is the function for getting the image
	 * all three below are similar so I will only add comments for the one
	 * no input
	 * output is the image of the correct wall (image file names hardcoded)
	 */
	public ImageIcon getLeftPic(){
		//0 means nothing to left
		//1 means the current room has a left turn and the next room doesn't
		//2 means the next room has a left turn but current doesn't
		//3 means both do
		String[] possiblePictures=new String[4];
		possiblePictures[0]="texture1_left.png";
		possiblePictures[1]="texture1_left_no_wall_back.png";
		possiblePictures[2]="texture1_left_no_wall_front.png";
		possiblePictures[3]="texture1_left_no_wall_both.png";
		int selector=0;
		//current room left?
		if(master.hasLeftHall()){
			selector=selector+1;
		}
		//next room left?
		if(master.nextRoomHasLeftHall()){
			selector=selector+2;
		}
		//get the right image
		return new ImageIcon(possiblePictures[selector]);
	}

	public ImageIcon getForwardPic(){
		String[] possiblePictures=new String[3];
		possiblePictures[0]="texture1_center_wall_in_face.png";
		possiblePictures[1]="texture1_center.png";
		possiblePictures[2]="texture1_center_no_wall_back.png";		
		int selector=0;
		if(master.hasForwardHall()){
			selector=selector+1;
		}
		if(master.nextRoomHasForwardHall()){
			selector=selector+1;
		}
		return new ImageIcon(possiblePictures[selector]);
	}

	public ImageIcon getRightPic(){
		String[] possiblePictures=new String[4];
		possiblePictures[0]="texture1_right.png";
		possiblePictures[1]="texture1_right_no_wall_back.png";
		possiblePictures[2]="texture1_right_no_wall_front.png";
		possiblePictures[3]="texture1_right_no_wall_both.png";
		int selector=0;
		if(master.hasRightHall()){
			selector=selector+1;
		}
		if(master.nextRoomHasRightHall()){
			selector=selector+2;
		}
		return new ImageIcon(possiblePictures[selector]);
	}

	@Override
	//mouse listener for the items
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Item clickedItem;		
		//if next room has items
		if(itemObservers.getItems().size()>0){				
			//if one of them was clicked
			if(itemObservers.getClickedNumber(arg0.getPoint())>-1){				
				clickedItem=master.getNumberItemNextRoom(itemObservers.getClickedNumber(arg0.getPoint()));
				//add the item to user and remove from room
				if(!master.userAtMaxItems()){
					master.addItemToUser(clickedItem);
					master.removeItemFromNextRoom(clickedItem);
					//then repaint
					painter.reDraw();
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
