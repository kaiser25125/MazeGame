package game;
/*
 * mediator for drawing the different parts of the game graphically
 */
public class CPCMediator {
	//all of the different parts that can be stored to redraw
	private CustomPaintMaze room;
	private CustomPaintToolBar toolBar;
	private CustomPaintRooms map;
	//all of the constructors take custom painted components if they have parameters
	
	//construct with the map
	public CPCMediator(CustomPaintToolBar bar,CustomPaintRooms map){
		toolBar=bar;
		map=this.map;
	}
	//construct without the map
	public CPCMediator(CustomPaintToolBar bar){
		toolBar=bar;
		map=null;
	}
	//dummy construct
	public CPCMediator(){}
	//redraw all of the components
	public void reDraw(){		
		System.out.println("activated");
		toolBar.invalidate();
		toolBar.repaint();
		if(map!=null){
			map.invalidate();
			map.repaint();
		}
		if(room!=null){
			room.invalidate();
			room.repaint();
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

	public CustomPaintRooms getMap() {
		return map;
	}

	public void setMap(CustomPaintRooms map) {
		this.map = map;
	}
	
}
