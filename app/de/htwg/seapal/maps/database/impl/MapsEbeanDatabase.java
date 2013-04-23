package de.htwg.seapal.maps.database.impl;

import com.avaje.ebean.Ebean;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.ebean.Maps;

public class MapsEbeanDatabase implements IMapsDatabase {

	@Override
	public void save(IMaps maps) {
		IMaps loadedMaps = load();
		
		if (loadedMaps != null) {
			Ebean.delete(loadedMaps);
		}
		
		Ebean.save(maps);
	}

	@Override
	public IMaps load() {
		return Ebean.find(Maps.class, 0);
	}

	@Override
	public boolean close() {
		return true;
	}

}
