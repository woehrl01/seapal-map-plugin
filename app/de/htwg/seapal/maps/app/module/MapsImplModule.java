package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.MapsFactory;
import de.htwg.seapal.maps.models.IMaps;

/**
 * Final Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsImplModule extends AbstractModule {

	@Override
	protected void configure() {
		
		install(new FactoryModuleBuilder()
    		.implement(IMaps.class, de.htwg.seapal.maps.models.impl.Maps.class)
    		.build(MapsFactory.class));   

	    bind(IMapsDatabase.class).to(de.htwg.seapal.maps.database.impl.MapsEbeanDatabase.class);	
	}
}
