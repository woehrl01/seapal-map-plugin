package de.htwg.seapal.maps.database.impl;

import javax.persistence.PersistenceException;

import play.Logger;

import com.avaje.ebean.Ebean;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.impl.Maps;

public class MapsEbeanDatabase implements IMapsDatabase {

	@Override
	public void save(IMaps maps) {	
		IMaps map = load();
		if(map != null){
			Ebean.delete(map);
		}
		
		Ebean.save(maps);
	}

	@Override
	public IMaps load() {
		IMaps maps = null;
		try{
			maps = Ebean.find(Maps.class).findUnique();
		}catch(PersistenceException e){
			System.out.println(e.getMessage());
		}
		Logger.info("Maps from Database is: " + maps);
			
		return maps;
	}

	@Override
	public boolean close() {
		return true;
	}

}
