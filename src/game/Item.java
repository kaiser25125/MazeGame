package game;

import javax.swing.ImageIcon;

public class Item {
	private String name;
	private int power;
	private String element;
	private ImageIcon image;
	
	public Item(String name, int power, String element, ImageIcon image){
		this.name=name;
		this.power=power;
		this.element=element;
		this.image=image;
	}

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
			
	
	
	public String toString(){
		String returner="";
		returner=returner+this.getName()+" "+Integer.toString(this.power)+" "+this.getElement()+" "+this.getImage().toString();
		return returner;
	}
	

}
