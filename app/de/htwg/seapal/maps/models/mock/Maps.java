package de.htwg.seapal.maps.models.mock;

import java.awt.Point;

import de.htwg.seapal.maps.models.AbstractMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

/**
 * Mock implementation of the Maps class.
 * @author Benjamin
 */
public class Maps extends AbstractMaps {
	
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
}
