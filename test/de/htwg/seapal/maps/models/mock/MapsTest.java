package de.htwg.seapal.maps.models.mock;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

public class MapsTest {

	private Maps maps;
	
	@Before
	public void setUp() throws Exception {
		maps = new Maps();
	}

	@Test
	public void testIsMenuVisible() {
		boolean expected = true;
		boolean actual = maps.getMenuVisible();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetMenuPositionSate() {
		MapsMenuPositionState expected = MapsMenuPositionState.LEFT;
		MapsMenuPositionState actual = maps.getMenuPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPositionSate() {
		MapsPositionState expected = MapsPositionState.FIXED;
		MapsPositionState actual = maps.getPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPosition() {
		String expected = "44.44,55.55";
		String actual = maps.getPosition();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetMapsType() {
		MapsType expected = MapsType.CARD;
		MapsType actual = maps.getType();
		
		assertEquals(expected, actual);
	}
}
