package game;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

public class RoomTest {

	@Test
	public void test() {
		Room x=new Room();
		x.setS(x);
		
		System.out.println(x);
	}

}
