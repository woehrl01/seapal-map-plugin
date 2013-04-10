package de.htwg.seapal.maps.models;

public abstract class AbstractMaps implements IMaps {

	private boolean isMenuVisible = true;
	
	@Override
	public boolean isMenuVisible() {
		return this.isMenuVisible;
	}

}
