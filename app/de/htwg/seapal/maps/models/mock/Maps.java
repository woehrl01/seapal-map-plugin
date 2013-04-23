package de.htwg.seapal.maps.models.mock;

import java.awt.Point;

import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

/**
 * Mock implementation of the Maps class.
 * @author Benjamin
 */
public class Maps implements IMaps {
	
	@Override
	public boolean isMenuVisible() {
		return true;
	}

	@Override
	public MapsMenuPositionState getMenuPositionState() {
		return MapsMenuPositionState.LEFT;
	}

	@Override
	public MapsPositionState getPositionState() {
		return MapsPositionState.FIXED;
	}

	@Override
	public Point getPosition() {
		return new Point();
	}

	@Override
	public MapsType getType() {
		return MapsType.CARD;
	}

	@Override
	public void setMenuVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPositionState(MapsPositionState positionState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(Point position) throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(MapsType type) {
		// TODO Auto-generated method stub
		
	}
}
