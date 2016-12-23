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

/*
 * This class draws the maze walls on the top graphics panel
 * also contains the mouse listener for the items in the maze and draws the monsters
 */
public class CustomPaintMaze extends JComponent implements MouseListener {
	GameMediator master;
	CPCObserver painter;
	ItemListener itemObservers;
	/*
	 * Takes the two mediators as inputs
	 * outputs the custom painted object
	 */
	public static final int MONSTER_IMAGE_SIZE=100;
	
	public static final int ITEM_IMAGE_SIZE=50;
	
	
	
	public CustomPaintMaze(GameMediator master, CPCObserver painter){
		this.master=master;
		this.painter=painter;
		this.setPreferredSize(new Dimension(GuiMaze.GAME_WIDTH,GuiMaze.GAME_LENGTH-GuiMaze.getToolBarSize()));
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
			int wallLength=GuiMaze.GAME_LENGTH-GuiMaze.getToolBarSize();
			int wallWidth=GuiMaze.GAME_WIDTH/3;
			int wallyPosition=0;
			int wallxPosition=0;
			g.drawImage(roomLeft.getImage(), wallxPosition, wallyPosition, wallWidth,wallLength , null);
			wallxPosition=GuiMaze.GAME_WIDTH/3;
			g.drawImage(roomForward.getImage(), wallxPosition, wallyPosition, wallWidth, wallLength, null);
			wallxPosition=GuiMaze.GAME_WIDTH-GuiMaze.GAME_WIDTH/3;
			g.drawImage(roomRight.getImage(), wallxPosition, wallyPosition, wallWidth, wallLength, null);
			//	draw the monsters for the game
			if(master.nextRoomHasMonster()){			
				Iterator<Monster> iterator=master.getNextRoomMonsters().iterator();
				Monster currentMonster;
				//values for processing
				int monsterCounter=0;
				int xPosition;
				int yPosition;
				//"constant" for the size of the monsters						
				//rect for healthbar
				Rectangle healthBar;
				int healthWidth;
				//parsing float
				float healthParse=MONSTER_IMAGE_SIZE;
				//while there is a next monster
				while(iterator.hasNext()){				
					currentMonster=iterator.next();
					xPosition=0;
					yPosition=0;								
					healthParse=MONSTER_IMAGE_SIZE;
					healthWidth=0;
					//if the monster is not dead
					if(!(currentMonster.getMonsterState() instanceof DeadState)){
						//draw all of the monsters
						xPosition=GuiMaze.GAME_WIDTH/3;
						xPosition=xPosition+((GuiMaze.GAME_WIDTH/master.getNextRoomMonsters().size()/3)*monsterCounter);
						yPosition=GuiMaze.GAME_LENGTH-GuiMaze.getToolBarSize()-GuiMaze.GAME_LENGTH/4;
						g.drawImage(currentMonster.getImage().getImage(), xPosition, yPosition, MONSTER_IMAGE_SIZE, MONSTER_IMAGE_SIZE,null);
					
						//draw the health bars
						yPosition=yPosition-yOffSet;
						//draw element weak to
						g.setColor(Color.white);
						g.fillRect(xPosition, yPosition-2*yOffSet, MONSTER_IMAGE_SIZE, MONSTER_IMAGE_SIZE/8);
						g.setColor(Color.black);
						//	needed new font
						g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
						g.drawString("Weak: "+currentMonster.getElement(), xPosition+5, yPosition-yOffSet);
						//draw outer green
						g.setColor(Color.green);
						g.drawRect(xPosition, yPosition, MONSTER_IMAGE_SIZE, MONSTER_IMAGE_SIZE/10);
						g.setColor(GuiMaze.getColor(currentMonster.getPercentHealth()));
						//draw the actual health
						healthParse=healthParse*currentMonster.getPercentHealth();					
						healthWidth=(int)healthParse;						
						g.fillRect(xPosition, yPosition, healthWidth, MONSTER_IMAGE_SIZE/10);
						monsterCounter=monsterCounter+1;
					}
				}
			}
			//if the next room has items
			if(master.nextRoomHasItem()){
				ArrayList<Item> items=master.getNextRoomItem();
				Iterator<Item> itemIterator=items.iterator();
				int itemCounter=0;
				int xPosition2;
				int yPosition2;						
				Item currentItem;
				Rectangle listener;
				//for each item
				while(itemIterator.hasNext()){
					//get the next item
					currentItem=itemIterator.next();
					//	calculate where to put it
					xPosition2=GuiMaze.GAME_WIDTH/3;
					xPosition2=xPosition2+((GuiMaze.GAME_WIDTH/items.size()/3)*itemCounter);
					yPosition2=GuiMaze.GAME_LENGTH-GuiMaze.getToolBarSize()-GuiMaze.GAME_LENGTH/7;
					//	create listener for item
					listener=new Rectangle(xPosition2,yPosition2,ITEM_IMAGE_SIZE,ITEM_IMAGE_SIZE);
					itemObservers.addListener(listener);
					//draw it
					g.drawImage(currentItem.getImage().getImage(), xPosition2, yPosition2, ITEM_IMAGE_SIZE, ITEM_IMAGE_SIZE,null);				
					itemCounter=itemCounter+1;
				}
			}
		}
		//if the user has won the game
		else if(master.userHasWon()){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			int xEndPos=this.getX()+(this.getWidth()/10);
			int yEndPos=this.getY()+this.getHeight()/2;
			g.drawString("You Win!!!!",xEndPos, yEndPos); 
		}
		//if the user died
		else if(!master.userHasWon()){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			int xEndPos=this.getX()+(this.getWidth()/10);
			int yEndPos=this.getY()+this.getHeight()/2;
			g.drawString("You Lose!!!!", xEndPos, yEndPos);
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
