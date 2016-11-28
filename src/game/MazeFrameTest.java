package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazeFrameTest {
	//need new tests
	@Test
	public void test() {
		MazeFrame x=new MazeFrame();
		User y=new User(20,"N",7);
		x.setPlayer(y);
		/*
		x.turnRight();
		assertTrue(y.getDirection().equals("E"));
		x.turnRight();
		assertTrue(y.getDirection().equals("S"));
		x.turnRight();
		assertTrue(y.getDirection().equals("W"));
		x.turnRight();
		assertTrue(y.getDirection().equals("N"));
		x.turnLeft();
		assertTrue(y.getDirection().equals("W"));
		x.turnLeft();
		assertTrue(y.getDirection().equals("S"));
		x.turnLeft();
		assertTrue(y.getDirection().equals("E"));
		x.turnLeft();
		assertTrue(y.getDirection().equals("N"));
		Room one=new Room();
		Room two=new Room();
		one.setW(two);
		x.setCurrentRoom(one);
		x.turnLeft();
		x.moveForward();
		assertTrue(x.currentRoom.equals(two));
		x.moveForward();
		assertTrue(x.currentRoom.equals(two));
		*/
	}

}
