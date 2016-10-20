package game;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

public class ItemTest {

	@Test
	public void test() {
		Item x=new Item("orb",5,"paper",new ImageIcon("orb.jpg"));
		assertTrue(x.toString().equals("orb 5 paper orb.jpg"));
		assertTrue(x.getPower()==5);
	}

}
