package de.htwg.seapal.maps.database.impl;

import javax.persistence.PersistenceException;

import com.avaje.ebean.Ebean;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.impl.Maps;

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
		try{
			return Ebean.find(Maps.class, 0);
		}catch(PersistenceException e){
			return null;
		}
	}

	@Override
	public boolean close() {
		return true;
	}

}
