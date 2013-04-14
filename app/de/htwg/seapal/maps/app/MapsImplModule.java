package de.htwg.seapal.maps.app;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.boat.models.IBoat;
import de.htwg.seapal.boat.views.tui.BoatTUI;
import de.htwg.seapal.person.views.tui.PersonTUI;
import de.htwg.util.plugin.Plugin;

/**
 * Final Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsImplModule extends AbstractModule {

	@Override
	protected void configure() {
		// TUI multibindings
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		plugins.addBinding().to(BoatTUI.class);
	    plugins.addBinding().to(PersonTUI.class);
	    
	    // bindings to dependent plugins
	    bind(IBoat.class).to(de.htwg.seapal.boat.models.impl.Boat.class);
		bind(IBoatController.class).to(de.htwg.seapal.boat.controllers.impl.BoatController.class);	
	}
}
