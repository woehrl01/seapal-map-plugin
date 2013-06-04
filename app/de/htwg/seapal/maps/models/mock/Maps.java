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
	public Long getId() {
		return 0L;
	}

	@Override
	public void setId(Long id) {
		
	}
	
	@Override
	public boolean getMenuVisible() {
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
		
	}

	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState) {
		
	}

	@Override
	public void setPositionState(MapsPositionState positionState) {
		
	}

	@Override
	public void setPosition(Point position) throws IllegalStateException {
		
	}

	@Override
	public void setType(MapsType type) {
		
	}
}
