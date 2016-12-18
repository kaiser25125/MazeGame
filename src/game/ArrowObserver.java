package game;

import java.awt.Point;
import java.awt.Polygon;
/*
 * this is a helper class to store the arrow icons used in the bottom part of the screen
 * it stores the polygons under respective directions and then has functions to determine if a point
 * lies inside one of the polygons
 */
public class ArrowObserver {
	//every direction polygon
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
	//return whether up polygon was clicked
	public boolean UpClicked(Point click){
		return Up.contains(click);
	}
	//return whether right polygon was clicked
	public boolean RightClicked(Point click){
		return Right.contains(click);
	}
	//return whether left polygon was clicked
	public boolean LeftClicked(Point click){
		return Left.contains(click);
	}
	
}
