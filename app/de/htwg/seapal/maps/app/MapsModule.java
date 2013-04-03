package de.htwg.seapal.maps.app;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.boatdemo.views.tui.BoatTUI;
import de.htwg.seapal.persondemo.views.tui.PersonTUI;
import de.htwg.util.plugin.Plugin;

public class MapsModule extends AbstractModule {

	@Override
	protected void configure() {		
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		plugins.addBinding().to(BoatTUI.class);
	    plugins.addBinding().to(PersonTUI.class);
		
	}

}
