package de.htwg.seapal.maps.app;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.boatdemo.controllers.IBoatController;
import de.htwg.seapal.boatdemo.models.IBoat;
import de.htwg.seapal.boatdemo.views.tui.BoatTUI;
import de.htwg.seapal.persondemo.views.tui.PersonTUI;
import de.htwg.util.plugin.Plugin;

public class MapsMockModule extends AbstractModule {
	@Override
	protected void configure() {
		// TUI multibindings
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		plugins.addBinding().to(BoatTUI.class);
	    plugins.addBinding().to(PersonTUI.class);
	    
	    // bindings to dependent plugins
	    bind(IBoat.class).to(de.htwg.seapal.boatdemo.models.mock.Boat.class);
		bind(IBoatController.class).to(de.htwg.seapal.boatdemo.controllers.mock.BoatController.class);	
	}
}
