package game;

import java.awt.Graphics;

import javax.swing.JComponent;
/*
 * custom painted component for displaying an items info inside of the game
 * used in item frame
 */
public class CustomItemPicture extends JComponent {
	private Item itemToDescr;
	public CustomItemPicture(Item display){
		itemToDescr=display;
	}
	public static final int cipPicSize=50;
	public void paint(Graphics g){
		//draw the image
		int xPosition=this.getX()+this.getWidth()/3;
		int yPosition=this.getY()+this.getHeight()/4;
		g.drawImage(itemToDescr.getImage().getImage(), xPosition, yPosition,cipPicSize,cipPicSize, null);
		//name
		xPosition=this.getX()+this.getWidth()/4;
		yPosition=this.getY()+this.getHeight()/2;
		g.drawString("Name: "+itemToDescr.getName(), xPosition, yPosition);
		//power/ damage item does
		xPosition=this.getX()+this.getWidth()/4;
		yPosition=this.getY()+this.getHeight()/2+20;
		g.drawString("Power: "+Integer.toString(itemToDescr.getPower()),xPosition, yPosition);		
		//what element the weapon is of
		xPosition=this.getX()+this.getWidth()/4;
		yPosition=this.getY()+this.getHeight()/2+40;
		g.drawString("Element: "+itemToDescr.getElement(),xPosition, yPosition);
	}
}
