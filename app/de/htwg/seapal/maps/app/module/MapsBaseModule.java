package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.views.web.hooks.HtmlRenderHook;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ExampleHtmlRenderHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ExampleMenuBarHook;
//import de.htwg.seapal.trip.views.web.hooks.impl.TripMenuBarHook;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {	    
		configureHtmlRenderHooks();
		configureMenuBarHooks();
		
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	}
	
	private void configureHtmlRenderHooks() {
		Multibinder<HtmlRenderHook> plugins = Multibinder.newSetBinder(binder(), HtmlRenderHook.class);
		plugins.addBinding().to(ExampleHtmlRenderHook.class);
	}
	
	private void configureMenuBarHooks() {
		Multibinder<MenuBarHook> plugins = Multibinder.newSetBinder(binder(), MenuBarHook.class);
		plugins.addBinding().to(ExampleMenuBarHook.class);
		//plugins.addBinding().to(TripMenuBarHook.class);
	}
}
