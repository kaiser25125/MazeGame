package edu.centre.csc300.luken.mazegame;

import java.util.ArrayList;

public class MonsterAnimationThread implements Runnable {
	private CPCObserver repatiner;
	private ArrayList<Monster> monsters;
	/*
	 * thread for animating monster
	 * takes the cpc observer as input and the arraylist of all monsters to animate
	 */
	public MonsterAnimationThread(CPCObserver repainter, ArrayList<Monster> monsters){
		this.monsters=monsters;
		this.repatiner=repainter;
	}
	//every two seconds switch the images
	@Override
	public void run() {
		while(true){
		for(int i=0; i<monsters.size(); i++){
			monsters.get(i).switchImage();
		}
		this.repatiner.reDraw();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
