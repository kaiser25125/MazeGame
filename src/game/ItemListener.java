package game;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * general observer for any box item
 */
public class ItemListener {
	//array to check for clicks
	ArrayList<Rectangle> items;
	//constructor
	public ItemListener(){
		items=new ArrayList<Rectangle>();
	}
	
	public ArrayList<Rectangle> getItems(){
		return items;
	}
	//takes a swing rectangle as input
	//no output
	//adds the rectangle to the listeners
	public void addListener(Rectangle add){
		items.add(add);
	}
	//takes a point as input
	//returns the index of the clicked rectangle in the items list
	public int getClickedNumber(Point click){
		int acc=0;
		Rectangle current;
		Iterator<Rectangle> iterator=items.iterator();
		//for each rect
		while(iterator.hasNext()){
			current=iterator.next();
			//if it has rect return index
			if(current.contains(click)){
				return acc;
			}
			acc++;
		}
		//no rect was clicked
		return -1;
	}

}
