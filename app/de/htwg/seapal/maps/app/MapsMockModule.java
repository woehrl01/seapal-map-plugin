package de.htwg.seapal.maps.app;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.boat.controllers.mock.BoatController;
import de.htwg.seapal.boat.models.IBoat;
import de.htwg.seapal.boat.models.mock.Boat;
import de.htwg.seapal.boat.views.tui.BoatTUI;
import de.htwg.seapal.person.views.tui.PersonTUI;
import de.htwg.util.plugin.Plugin;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsMockModule extends AbstractModule {
	@Override
	protected void configure() {
		// TUI multibindings
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		plugins.addBinding().to(BoatTUI.class);
	    plugins.addBinding().to(PersonTUI.class);
	    
	    // bindings to dependent plugins
	    bind(IBoat.class).to(Boat.class);
		bind(IBoatController.class).to(BoatController.class);	
	}
}
