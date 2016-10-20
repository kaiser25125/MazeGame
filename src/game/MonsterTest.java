package game;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

public class MonsterTest {

	@Test
	public void test() {
		//(int health, int power, String name, String element,int weakness,ImageIcon image) {
		Monster x=new Monster(20,20,"dragon","ice",4,new ImageIcon("orb.jpg"));
		Item orb=new Item("orb",2,"ice",new ImageIcon("orb.jpg"));
		Item axe=new Item("axe",2,"fire",new ImageIcon("orb.jpg"));
		x.takeDamage(orb);		
		assertTrue(x.getHealth()==12);
		x.takeDamage(axe);
		assertTrue(x.getHealth()==10);
		x.takeDamage(orb);
		x.takeDamage(orb);
		assertFalse(x.isAlive());
		
	}

}
