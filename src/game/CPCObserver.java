package game;
/*
 * Observer for drawing the different parts of the game graphically
 * Passed as a param almost everywhere
 * Should be globally available and there should only be one copy
 * didn't want to use singleton
 */
public class CPCObserver {
	//all of the different parts that can be stored to redraw
	private CustomPaintMaze room;
	private CustomPaintToolBar toolBar;
	private CustomPaintMap map;
	//all of the constructors take custom painted components if they have parameters
	
	//construct with the map
	public CPCObserver(CustomPaintToolBar bar,CustomPaintMap map){
		toolBar=bar;
		map=this.map;
	}
	//construct without the map
	public CPCObserver(CustomPaintToolBar bar){
		toolBar=bar;
		map=null;
	}
	//dummy construct
	public CPCObserver(){}
	//redraw all of the components
	public void reDraw(){	
		if(room!=null){
			room.invalidate();
			room.repaint();
		}
		toolBar.invalidate();
		toolBar.repaint();		
		if(map!=null){
			map.invalidate();
			map.repaint();
		}

		
	}
	//getters and setters	
	public CustomPaintMaze getRoom() {
		return room;
	}

	public void setRoom(CustomPaintMaze room) {
		this.room = room;
	}

	public CustomPaintToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(CustomPaintToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public CustomPaintMap getMap() {
		return map;
	}

	public void setMap(CustomPaintMap map) {
		this.map = map;
	}
	
}
