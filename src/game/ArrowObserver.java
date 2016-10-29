package game;

import java.awt.Point;
import java.awt.Polygon;
/*
 * this is the observer for the graphic arrows
 * stores them and checks them based on mouse inputs
 */
public class ArrowObserver {
	//every direction
	private Polygon Up;
	private Polygon Left;
	private Polygon Right;
	
	public ArrowObserver(){}
	//setters
	public void setUp(Polygon up) {
		Up = up;
	}

	public void setLeft(Polygon left) {
		Left = left;
	}

	public void setRight(Polygon right) {
		Right = right;
	};
	//getters
	public Polygon getUp() {
		return Up;
	}

	public Polygon getLeft() {
		return Left;
	}

	public Polygon getRight() {
		return Right;
	}
	//return whether up was clicked
	public boolean UpClicked(Point click){
		return Up.contains(click);
	}
	//return whether right direction was clicked
	public boolean RightClicked(Point click){
		return Right.contains(click);
	}
	//return whether left was clicked
	public boolean LeftClicked(Point click){
		return Left.contains(click);
	}
	
}
