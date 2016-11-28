package game;

import java.awt.Graphics;

import javax.swing.JComponent;
/*
 * custom painted component for displaying the items info
 * used in item frame
 */
public class CustomItemPicture extends JComponent {
	private Item itemToDescr;
	public CustomItemPicture(Item display){
		itemToDescr=display;
	}
	
	public void paint(Graphics g){
		//draw the image
		g.drawImage(itemToDescr.getImage().getImage(), this.getX()+this.getWidth()/3, this.getY()+this.getHeight()/4,50,50, null);
		//name
		g.drawString("Name: "+itemToDescr.getName(), this.getX()+this.getWidth()/4, this.getY()+this.getHeight()/2);
		//power/ damage item does
		g.drawString("Power: "+Integer.toString(itemToDescr.getPower()),this.getX()+this.getWidth()/4, this.getY()+this.getHeight()/2+20);
		//what element the weapon is of
		g.drawString("Element: "+itemToDescr.getElement(),this.getX()+this.getWidth()/4, this.getY()+this.getHeight()/2+40);
	}
}
