package de.htwg.seapal.maps.app.module;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.impl.StdCouchDbConnector;

import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.MapsFactory;
import de.htwg.seapal.maps.models.IMaps;

/**
 * Final Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsImplModule extends MapsBaseModule {

	@Override
	protected void configure() {
		super.configure();
		
		install(new FactoryModuleBuilder()
    		.implement(IMaps.class, de.htwg.seapal.maps.models.impl.Maps.class)
    		.build(MapsFactory.class));   

		bind(String.class).annotatedWith(Names.named("databaseOfMaps")).toInstance("maps_db");
	    bind(IMapsDatabase.class).to(de.htwg.seapal.maps.database.impl.MapsCouchDbDatabase.class);	
	}
	
	@Provides
	@Named("mapsCouchDbConnector")
	CouchDbConnector getPersonStdCouchDbConnector(@Named("databaseOfMaps") String databaseName, CouchDbInstance couchDbInstance) {
		return new StdCouchDbConnector(databaseName, couchDbInstance);
	}
}
