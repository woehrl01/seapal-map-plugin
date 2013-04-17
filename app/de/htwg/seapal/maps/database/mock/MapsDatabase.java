package de.htwg.seapal.maps.database.mock;

import java.awt.Point;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;
import de.htwg.seapal.maps.models.mock.Maps;

public class MapsDatabase implements IMapsDatabase {
	
	@Override
	public void save(IMaps maps) {

	}

	@Override
	public IMaps load() {
		Maps maps = new Maps();
		maps.setMenuPositionState(MapsMenuPositionState.LEFT);
		maps.setPositionState(MapsPositionState.FIXED);
		maps.setPosition(new Point());
		maps.setType(MapsType.CARD);
		return maps;
	}

	@Override
	public boolean close() {
		return true;
	}

}
