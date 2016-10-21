package game;

import javax.swing.ImageIcon;
/*
 * items to use as weapons for the player
 */
public class Item {
	//name of item
	private String name;
	//damage item does to monsters
	private int power;
	//element of item, deals extra damage if monster is weak to it
	private String element;
	//image of the item
	private ImageIcon image;
	//general constructor
	public Item(String name, int power, String element, ImageIcon image){
		this.name=name;
		this.power=power;
		this.element=element;
		this.image=image;
	}
	/*
	 * general getters and setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
			
	
	//general toString
	public String toString(){
		String returner="";
		returner=returner+this.getName()+" "+Integer.toString(this.power)+" "+this.getElement()+" "+this.getImage().toString();
		return returner;
	}
	

}
