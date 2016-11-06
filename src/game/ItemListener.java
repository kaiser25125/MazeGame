package game;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;


public class ItemListener {
	
	ArrayList<Rectangle> items;
	
	public ItemListener(){
		items=new ArrayList<Rectangle>();
	}
	
	public ArrayList<Rectangle> getItems(){
		return items;
	}
	
	public void addListener(Rectangle add){
		items.add(add);
	}
	
	public int getClickedNumber(Point click){
		int acc=0;
		Rectangle current;
		Iterator<Rectangle> iterator=items.iterator();
		while(iterator.hasNext()){
			current=iterator.next();
			if(current.contains(click)){
				return acc;
			}
			acc++;
		}
		return -1;
	}

}
