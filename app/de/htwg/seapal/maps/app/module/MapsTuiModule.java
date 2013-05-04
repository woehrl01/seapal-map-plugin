package de.htwg.seapal.maps.app.module;

import java.util.List;
import java.util.ServiceLoader;

import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;

import de.devsurf.injection.guice.annotations.Bind;
import de.htwg.seapal.maps.views.tui.states.InMenuState;
import de.htwg.seapal.maps.views.tui.states.InMenuStateFactory;
import de.htwg.seapal.maps.views.tui.states.InPluginState;
import de.htwg.seapal.maps.views.tui.states.InPluginStateFactory;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.web.hooks.impl.AnotherConcreteTableHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ConcreteTableHook;
import de.htwg.seapal.person.views.tui.PersonTUI;
import de.htwg.seapal.trip.views.tui.TripTUI;
import de.htwg.seapal.boat.views.tui.BoatTUI;
import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.TuiState;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsTuiModule extends AbstractModule {
	@Override
	protected void configure() {
		configureTUI();
		configureStates();  
	}
	
	private void configureTUI() {
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		//plugins.addBinding().to(TripTUI.class);
		plugins.addBinding().to(BoatTUI.class);
		plugins.addBinding().to(PersonTUI.class);
	}
	
	private void configureStates() {
		bind(TuiState.class).annotatedWith(Names.named("Initial")).to(InMenuState.class);
		
	    install(new FactoryModuleBuilder()
	    	.implement(InMenuState.class, InMenuState.class)
	    	.build(InMenuStateFactory.class));
	    
	    install(new FactoryModuleBuilder()
	    	.implement(InPluginState.class, InPluginState.class)
	    	.build(InPluginStateFactory.class)); 
	}
}
