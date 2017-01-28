package edu.centre.csc300.luken.mazegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenerateMazeTest {

	@Test
	public void test() {		
		GenerateMaze x=new GenerateMaze();
		x.getStartRoom();
		System.out.println(x);
	}

}
