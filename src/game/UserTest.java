package game;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

public class UserTest {

	@Test
	public void test() {
		User x=new User(20,"N");
		assertTrue(x.isAlive());
		x.takeDamage(20);
		assertFalse(x.isAlive());
		Item y=new Item("orb",5,"paper",new ImageIcon("orb.jpg"));
		x.addItem(y);
		assertTrue(x.getItems().contains(y));
	}

}
