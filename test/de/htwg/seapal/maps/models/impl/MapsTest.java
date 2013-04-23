package de.htwg.seapal.maps.models.impl;

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
	public void testShowMenu() {
		maps.showMenu();
		
		boolean expected = true;
		boolean actual = maps.getMenuVisible();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHideMenu() {
		maps.hideMenu();
		
		boolean expected = false;
		boolean actual = maps.getMenuVisible();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testMenuPositionSateToLeft() {
		maps.setMenuPositionState(MapsMenuPositionState.LEFT);
		
		MapsMenuPositionState expected = MapsMenuPositionState.LEFT;
		MapsMenuPositionState actual = maps.getMenuPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMenuPositionSateToRight() {
		maps.setMenuPositionState(MapsMenuPositionState.RIGHT);
		
		MapsMenuPositionState expected = MapsMenuPositionState.RIGHT;
		MapsMenuPositionState actual = maps.getMenuPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPositionSateToFixed() {
		maps.setPositionState(MapsPositionState.FIXED);
		
		MapsPositionState expected = MapsPositionState.FIXED;
		MapsPositionState actual = maps.getPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPositionSateToBoatPosition() {
		maps.setPositionState(MapsPositionState.BOAT_POSITION);
		
		MapsPositionState expected = MapsPositionState.BOAT_POSITION;
		MapsPositionState actual = maps.getPositionState();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMapsPosition() {
		maps.setPosition(new Point());
		
		Point expected = new Point();
		Point actual = maps.getPosition();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMapsTypeToCard() {
		maps.setType(MapsType.CARD);
		
		MapsType expected = MapsType.CARD;
		MapsType actual = maps.getType();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMapsTypeToSatelite() {
		maps.setType(MapsType.SATELITE);
		
		MapsType expected = MapsType.SATELITE;
		MapsType actual = maps.getType();
		
		assertEquals(expected, actual);
	}
}
