package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsFactory;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsMockModule extends AbstractModule {
	@Override
	protected void configure() {

	    install(new FactoryModuleBuilder()
			.implement(IMaps.class, de.htwg.seapal.maps.models.mock.Maps.class)
			.build(MapsFactory.class));
	    
	    bind(IMapsDatabase.class).to(de.htwg.seapal.maps.database.mock.MapsDatabase.class);	
	}
}
