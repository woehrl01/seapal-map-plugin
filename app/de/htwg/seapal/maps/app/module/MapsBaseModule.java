package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.views.web.hooks.AnotherConcreteTableHook;
import de.htwg.seapal.maps.views.web.hooks.ConcreteTableHook;
import de.htwg.seapal.maps.views.web.hooks.TableHook;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {	    
		Multibinder<TableHook> plugins = Multibinder.newSetBinder(binder(), TableHook.class);
		plugins.addBinding().to(ConcreteTableHook.class);
		plugins.addBinding().to(AnotherConcreteTableHook.class);
		
		
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	}
}
