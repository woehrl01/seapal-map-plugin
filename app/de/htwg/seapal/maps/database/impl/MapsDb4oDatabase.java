package de.htwg.seapal.maps.database.impl;

import java.util.List;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.impl.Maps;

public class MapsDb4oDatabase implements IMapsDatabase {

	/**
	 * The Db4o database.
	 */
	private ObjectContainer db;
	
	/**
	 * Instantiates a new maps database using Db4o.
	 */
	public MapsDb4oDatabase() {
		db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(),
				"maps.data");
	}
	
	@Override
	public void save(IMaps maps) {
		db.store(maps);
	}

	@Override
	public IMaps load() {
		List<IMaps> maps = db.query(IMaps.class);
		
		if (maps.size() == 0) {
			return new Maps();
		}
		
		return maps.get(0);
	}

	@Override
	public boolean close() {
		return db.close();
	}

}
