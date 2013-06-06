package de.htwg.seapal.maps.database.impl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;

import play.Logger;

import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.impl.Maps;

public class MapsCouchDbDatabase extends CouchDbRepositorySupport<Maps> implements IMapsDatabase {
	
	@Inject
	public MapsCouchDbDatabase(@Named("mapsCouchDbConnector") CouchDbConnector db) throws MalformedURLException {
		super(Maps.class, db);
	}
	
	@Override
	public void save(IMaps maps) {
		// load old settings value and delete it
		Maps m = get(maps.getId());
		remove(m);
		
		update((Maps)maps);
	}

	@Override
	@GenerateView
	public IMaps load() {

		List<Maps> results = getAll();
		
		if (results.size() > 0) {
			Logger.info("Loaded ID: " + results.get(0).getId());
			return (IMaps)results.get(0);
		}
		return null;
	}

	@Override
	public boolean close() {
		return true;
	}

}
