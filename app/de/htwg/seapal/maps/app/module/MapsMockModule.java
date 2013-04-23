package de.htwg.seapal.maps.app.module;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsMockModule extends MapsBaseModule {
	@Override
	protected void configure() {
	    super.configure();
	    // component bindings
	    bind(IMaps.class).to(de.htwg.seapal.maps.models.mock.Maps.class);
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	    bind(IMapsDatabase.class).to(de.htwg.seapal.maps.database.mock.MapsDatabase.class);	
	}
}
