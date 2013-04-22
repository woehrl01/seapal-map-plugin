package de.htwg.seapal.maps.models;

import java.awt.Point;

import javax.persistence.Entity;

/**
 * Abstract class of the maps component.
 * 
 * @author Benjamin
 * 
 */
@Entity
public abstract class AbstractMaps implements IMaps {

	private boolean isMenuVisible = true;
	private MapsMenuPositionState menuPositionState = MapsMenuPositionState.LEFT;
	private MapsPositionState positionState = MapsPositionState.FIXED;
	private Point position = new Point();
	private MapsType type = MapsType.CARD;

	@Override
	public boolean isMenuVisible() {
		return this.isMenuVisible;
	}
	
	@Override
	public void setMenuVisible( boolean visible ) {
		this.isMenuVisible = visible;
	}

	@Override
	public void hideMenu() {
		this.isMenuVisible = false;
	}

	@Override
	public void showMenu() {
		this.isMenuVisible = true;
	}

	@Override
	public MapsMenuPositionState getMenuPositionState() {
		return this.menuPositionState;
	}

	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState) {
		this.menuPositionState = menuPositionState;
	}

	@Override
	public MapsPositionState getPositionState() {
		return this.positionState;
	}

	@Override
	public void setPositionState(MapsPositionState positionState) {
		this.positionState = positionState;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Point position) throws IllegalStateException {
		if (getPositionState() != MapsPositionState.FIXED) {
			throw new IllegalStateException(
					"It is not allowed to set the maps position"
							+ "when the maps position state is not FIXED.");
		}

		this.position = position;
	}

	@Override
	public MapsType getType() {
		return this.type;
	}

	@Override
	public void setType(MapsType type) {
		this.type = type;
	}
}
