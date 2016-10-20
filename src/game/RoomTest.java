package game;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

public class RoomTest {

	@Test
	public void test() {
		Room x=new Room();
		x.setS(x);
		x.setMonster(new Monster(20,20,"dragon","ice",4,new ImageIcon("orb.jpg")));
		System.out.println(x);
	}

}
