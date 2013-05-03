package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.web.hooks.impl.AnotherConcreteTableHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ConcreteTableHook;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {	    
		configureMenuBarHooks();
		
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	}
	
	private void configureMenuBarHooks() {
		Multibinder<MenuBarHook> plugins = Multibinder.newSetBinder(binder(), MenuBarHook.class);
		plugins.addBinding().to(ConcreteTableHook.class);
		plugins.addBinding().to(AnotherConcreteTableHook.class);
	}
}
