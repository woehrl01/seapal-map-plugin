package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;

import de.htwg.seapal.maps.controllers.IMapsController;
/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {	    
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	}
}
