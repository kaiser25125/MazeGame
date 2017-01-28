package edu.centre.csc300.luken.mazegame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ItemFrame {
	/*
	 * this is the class that displays the item info and holds the custom painted component
	 * takes an item as input to create
	 */
	protected JFrame master;
	protected JPanel picture;
	protected CustomItemPicture cpc;
	public ItemFrame(Item display){
		//create jframe
		master=new JFrame();
		master.setSize(200, 300);
		master.setLayout(new BorderLayout());
		//create panel
		picture=new JPanel();
		picture.setLayout(new BorderLayout());
		//throw the item painting inside
		cpc=new CustomItemPicture(display);
		picture.add(cpc,BorderLayout.CENTER);
		master.add(picture,BorderLayout.CENTER);
		master.setVisible(true);
	}
}
