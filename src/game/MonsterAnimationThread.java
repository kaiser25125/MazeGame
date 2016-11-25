package game;

import java.util.ArrayList;

public class MonsterAnimationThread implements Runnable {
	private CPCMediator repatiner;
	private ArrayList<Monster> monsters;
	
	public MonsterAnimationThread(CPCMediator repainter, ArrayList<Monster> monsters){
		this.monsters=monsters;
		this.repatiner=repainter;
	}

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
