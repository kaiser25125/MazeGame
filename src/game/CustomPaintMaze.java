package game;

import java.awt.Dimension;
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
		//if the next room has items
		if(master.nextRoomHasItem()){
			ArrayList<Item> items=master.getNextRoomItem();
			Iterator<Item> itemIterator=items.iterator();
			int acc=0;
			int xPosition;
			int yPosition;
			int xSize=50;
			int ySize=50;
			Item currentItem;
			Rectangle listener;
			//for each item
			while(itemIterator.hasNext()){
				//get the next item
				currentItem=itemIterator.next();
				//calculate where to put it
				xPosition=GuiMaze.gameWidth/3;
				xPosition=xPosition+((GuiMaze.gameWidth/items.size()/3)*acc);
				yPosition=GuiMaze.gameLength-GuiMaze.getToolBarSize()-GuiMaze.gameLength/7;
				//create listener for item
				listener=new Rectangle(xPosition,yPosition,xSize,ySize);
				itemObservers.addListener(listener);
				//draw it
				g.drawImage(currentItem.getImage().getImage(), xPosition, yPosition, xSize, ySize,null);				
				acc=acc+1;
			}
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
		System.out.println("Top screen click");
		//if next room has items
		if(itemObservers.getItems().size()>0){	
			System.out.println("Top screen click 2");
			//if one of them was clicked
			if(itemObservers.getClickedNumber(arg0.getPoint())>-1){
				System.out.println("Top screen click 3");
				clickedItem=master.getNumberItemNextRoom(itemObservers.getClickedNumber(arg0.getPoint()));
				//add the item to user and remove from room
				master.addItemToUser(clickedItem);
				master.removeItemFromNextRoom(clickedItem);
				//then repaint
				painter.reDraw();
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
